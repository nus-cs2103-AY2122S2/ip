package mcbot;

import mcbot.exception.InvalidCommandException;
import mcbot.exception.McBotException;
import mcbot.task.Deadline;
import mcbot.task.Event;
import mcbot.task.Task;
import mcbot.task.ToDo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class McBotGui extends McBot {
    private final Gui gui;
    
    public McBotGui() {
        super("data/tasks.txt");
        gui = new Gui();
    }
    
    public String getResponse(String input) {
        Parser parser = new Parser(input);
            try {
                String keyCommand = parser.getKeyCommand();
                switch(keyCommand) {
                case "bye": {
                    return gui.byeLine();
                }
                case "list":
                    try {
                        if (tasks.size() == 0) {
                            throw new McBotException("Your list is empty boi");
                        }
                        return gui.listTask(tasks.getList());
                    } catch (McBotException e) {
                        return gui.printError(e);
                    }
                case "mark":
                    try {
                        String numStr = parser.getDetails();
                        int num = Integer.parseInt(numStr);
                        if (num < 1 || num > tasks.size()) {
                            throw new McBotException();
                        }
                        Task t = tasks.get(num - 1);
                        if (!t.isMarked()) {
                            t.markDone();
                            storage.updateData(tasks.getList());
                            return gui.markLine() + "\n" + gui.printTask(t);
                        } else {
                            return gui.markDuplication();
                        }
                    } catch (ArrayIndexOutOfBoundsException | InvalidCommandException e) {
                        return gui.markError("missingData");
                    } catch (NumberFormatException e) {
                        return gui.markError("notInteger");
                    } catch (McBotException e) {
                        return gui.markError("integerNotFound");
                    }
                case "unmark":
                    try {
                        String numStr = parser.getDetails();
                        int num = Integer.parseInt(numStr);
                        if (num < 1 || num > tasks.size()) {
                            throw new McBotException();
                        }
                        Task t = tasks.get(num - 1);
                        if (t.isMarked()) {
                            t.undoDone();
                            storage.updateData(tasks.getList());
                            return gui.unmarkLine() + "\n" + gui.printTask(t);
                        } else {
                            return gui.unmarkDuplication();
                        }
                    } catch (ArrayIndexOutOfBoundsException | InvalidCommandException e) {
                        return gui.markError("missingData");
                    } catch (NumberFormatException e) {
                        return gui.markError("notInteger");
                    } catch (McBotException e) {
                        return gui.markError("integerNotFound");
                    }
                case "todo":
                    try {
                        String taskName = parser.getDetails();
                        Task t = new ToDo(taskName);
                        tasks.add(t);
                        storage.appendData(t);
                        return gui.addTodoLine(t, tasks.size());
                    } catch (InvalidCommandException | ArrayIndexOutOfBoundsException e) {
                        return gui.taskError("emptyTask");
                    }
                case "deadline":
                    try {
                        String taskName = parser.getDeadlineTask();
                        Task t;
                        if (taskName.isBlank()) {
                            throw new InvalidCommandException("you can't leave your deadline task empty");
                        }
                        String dateStr = parser.getDeadlineDate();
                        LocalDate deadlineDate = LocalDate.parse(dateStr, dateFormatter);
                        if (dateStr.isBlank()) {
                            throw new InvalidCommandException("you can't leave your deadline date empty");
                        }
                        if (parser.isThereTime()) {
                            String timeStr = parser.getDeadlineTime();
                            LocalTime deadlineTime = LocalTime.parse(timeStr, timeFormatter);
                            t = new Deadline(taskName, deadlineDate, deadlineTime);
                        } else {
                            t = new Deadline(taskName, deadlineDate);
                        }
                        tasks.add(t);
                        storage.appendData(t);
                        return gui.addDeadlineLine(t, tasks.size());
                    } catch (InvalidCommandException e) {
                        return gui.printError(e);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        return gui.taskError("deadlineFormat");
                    } catch (DateTimeParseException e) {
                        return gui.taskError("datetimeFormat");
                    }
                case "event":
                    try {
                        String taskName = parser.getEventTask();
                        Task t;
                        if (taskName.isBlank()) {
                            throw new InvalidCommandException("you can't leave your event task empty");
                        }
                        String dateStr = parser.getEventDate();
                        LocalDate eventDate = LocalDate.parse(dateStr, dateFormatter);
                        if (dateStr.isBlank()) {
                            throw new InvalidCommandException("you can't leave your event date/time empty");
                        }
                        if (parser.isThereTime()) {
                            String timeStr = parser.getEventTime();
                            LocalTime eventTime = LocalTime.parse(timeStr, timeFormatter);
                            t = new Event(taskName, eventDate, eventTime);
                        } else {
                            t = new Event(taskName, eventDate);
                        }
                        tasks.add(t);
                        storage.appendData(t);
                        return gui.addEventLine(t, tasks.size());
                    } catch (InvalidCommandException e) {
                        return gui.printError(e);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        return gui.taskError("eventFormat");
                    } catch (DateTimeParseException e) {
                        return gui.taskError("datetimeFormat");
                    }
                case "delete":
                    try {
                        String numStr = parser.getDetails();
                        int num = Integer.parseInt(numStr);
                        if (num < 1 || num > tasks.size()) {
                            throw new McBotException("Boi, I can't delete a number that ain't on the list");
                        }
                        Task t = tasks.get(num - 1);
                        tasks.remove(num - 1);
                        storage.updateData(tasks.getList());
                        return gui.deleteLine(t, tasks.size());
                    } catch (InvalidCommandException | ArrayIndexOutOfBoundsException e) {
                        return gui.deleteError("empty");
                    } catch (McBotException e) {
                        return gui.printError(e);
                    } catch (NumberFormatException e) {
                        return gui.deleteError("notInteger");
                    }
                case "find":
                    try {
                        String taskName = parser.getDetails();
                        return tasks.find(taskName, gui);
                    } catch (InvalidCommandException | ArrayIndexOutOfBoundsException e) {
                        return gui.taskError("emptyFindTask");
                    }
                default:
                    throw new InvalidCommandException("I don't understand a word ye're sayin'");
                }
            } catch (McBotException e) {
                return gui.printError(e);
            } finally {
                parser.close();
            }
    }
}
