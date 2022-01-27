package mcbot;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import mcbot.task.Task;
import mcbot.task.ToDo;
import mcbot.task.Event;
import mcbot.task.Deadline;

import mcbot.exception.McBotException;
import mcbot.exception.InvalidCommandException;

public class McBot {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private String filePath;
    private boolean isRunning;
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
    
    public McBot(String filePath) {
        ui = new Ui();
        this.filePath = filePath;
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (McBotException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    
    public void run() {
        isRunning = true;
        ui.welcomeLine();
        Parser parser = new Parser();
        while(isRunning) {
            try {
                parser.readFullCommand();
                String keyCommand = parser.getKeyCommand();
                ui.printFrame();
                switch(keyCommand) {
                case "bye": {
                    ui.byeLine();
                    isRunning = false;
                    break;
                }
                case "list":
                    try {
                        if (tasks.size() == 0) {
                            throw new McBotException("Your list is empty boi");
                        }
                        ui.listTask(tasks.getList());
                    } catch (McBotException e) {
                        ui.printError(e);
                    }
                    break;
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
                            ui.markLine();
                            ui.printTask(t);
                        } else {
                            ui.markDuplication();
                        }
                    } catch (ArrayIndexOutOfBoundsException | InvalidCommandException e) {
                        ui.markError("missingData");
                    } catch (NumberFormatException e) {
                        ui.markError("notInteger");
                    } catch (McBotException e) {
                        ui.markError("integerNotFound");
                    }
                    break;
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
                            ui.unmarkLine();
                            ui.printTask(t);
                        }
                        else {
                            ui.unmarkDuplication();
                        }
                    } catch (ArrayIndexOutOfBoundsException | InvalidCommandException e) {
                        ui.markError("missingData");
                    } catch (NumberFormatException e) {
                        ui.markError("notInteger");
                    } catch (McBotException e) {
                        ui.markError("integerNotFound");
                    }
                    break;
                case "todo":
                    try {
                        String taskName = parser.getDetails();
                        Task t = new ToDo(taskName);
                        tasks.add(t);
                        storage.appendData(t);
                        ui.addTodoLine(t, tasks.size());
                    } catch (InvalidCommandException | ArrayIndexOutOfBoundsException e) {
                        ui.taskError("emptyTask");
                    }
                    break;
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
                        ui.addDeadlineLine(t, tasks.size());
                    } catch (InvalidCommandException e) {
                        ui.printError(e);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        ui.taskError("deadlineFormat");
                    } catch (DateTimeParseException e) {
                        ui.taskError("datetimeFormat");
                    }
                    break;
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
                        ui.addEventLine(t, tasks.size());
                    } catch (InvalidCommandException e) {
                        ui.printError(e);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        ui.taskError("eventFormat");
                    } catch (DateTimeParseException e) {
                        ui.taskError("datetimeFormat");
                    }
                    break;
                case "delete":
                    try {
                        String numStr = parser.getDetails();
                        int num = Integer.parseInt(numStr);
                        if (num < 1 || num > tasks.size()) {
                            throw new McBotException("Boi, I can't delete a number that ain't on the list");
                        }                        Task t = tasks.get(num - 1);
                        tasks.remove(num - 1);
                        storage.updateData(tasks.getList());
                        ui.deleteLine(t, tasks.size());
                    } catch (InvalidCommandException | ArrayIndexOutOfBoundsException e) {
                        ui.deleteError("empty");
                    } catch (McBotException e) {
                        ui.printError(e);
                    } catch (NumberFormatException e) {
                        ui.deleteError("notInteger");
                    }
                    break;
                default:
                    throw new InvalidCommandException("I don't understand a word ye're sayin'");
                }
            } catch (McBotException e) {
                ui.printError(e);
            } finally {
                ui.printFrame();
            }
        }
        parser.close();
    }
    
    public static void main(String[] args) {
        new McBot("data/tasks.txt").run();
    }
}
