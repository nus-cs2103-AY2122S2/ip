package mcbot;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import mcbot.exception.AnomalyException;
import mcbot.exception.InvalidCommandException;
import mcbot.exception.McBotException;
import mcbot.task.Deadline;
import mcbot.task.Event;
import mcbot.task.Task;
import mcbot.task.ToDo;

/**
 * McBotGui class for the chat-bot.
 */
public class McBotGui {
    protected final String filePath = "data/tasks.txt";
    protected Storage storage;
    protected TaskList tasks;
    protected Gui gui;
    protected DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
    protected DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");

    /**
     * Constructor for McBot GUI chat-bot.
     */
    public McBotGui() {
        gui = new Gui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (McBotException e) {
            gui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Method to get the response based on the input command.
     *
     * @param input is the command given by the user.
     * @return the String response based on the input.
     */
    public String getResponse(String input) {
        Parser parser = new Parser(input);
        try {
            String keyCommand = parser.getKeyCommand();
            switch(keyCommand) {
            case "hi":
                return gui.welcomeLine();
            case "bye":
                return executeBye();
            case "list":
                return executeList();
            case "mark":
                return executeMark(parser);
            case "unmark":
                return executeUnmark(parser);
            case "todo":
                return executeTodo(parser);
            case "deadline":
                return executeDeadline(parser);
            case "event":
                return executeEvent(parser);
            case "delete":
                return executeDelete(parser);
            case "find":
                return executeFind(parser);
            default:
                throw new InvalidCommandException("I don't understand a word ye're sayin'");
            }
        } catch (McBotException e) {
            return gui.printError(e);
        } finally {
            parser.close();
        }
    }

    /**
     * Method to execute bye command.
     *
     * @return goodbye line.
     */
    private String executeBye() {
        return "bye";
    }

    /**
     * Method to execute find. 
     *
     * @param parser to parse the information.
     * @return the task if found any.
     */
    private String executeFind(Parser parser) {
        try {
            String taskName = parser.getDetails();
            return tasks.find(taskName);
        } catch (InvalidCommandException | ArrayIndexOutOfBoundsException e) {
            return gui.taskError("emptyFindTask");
        }
    }

    /**
     * Method to execute delete.
     *
     * @param parser to parse the information.
     * @return the deleted task.
     */
    private String executeDelete(Parser parser) {
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
    }

    /**
     * Method to execute event.
     *
     * @param parser to parse the information.
     * @return the event task created.
     */
    private String executeEvent(Parser parser) {
        try {
            Event t = createEvent(parser);
            if (tasks.hasAnomaly(t)) {
                throw new AnomalyException();
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
        } catch (AnomalyException e) {
            return gui.anomalyError();
        }
    }

    /**
     * Method to execute deadline.
     *
     * @param parser to parse the information.
     * @return the deadline task created.
     */
    private String executeDeadline(Parser parser) {
        try {
            Task t = createDeadline(parser);
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
    }

    /**
     * Method to execute todo.
     *
     * @param parser to parse the information.
     * @return the todo task created.
     */
    private String executeTodo(Parser parser) {
        try {
            String taskName = parser.getDetails();
            assert !taskName.isBlank() : "Task name should not be blank";
            Task t = new ToDo(taskName);
            tasks.add(t);
            storage.appendData(t);
            return gui.addTodoLine(t, tasks.size());
        } catch (InvalidCommandException | ArrayIndexOutOfBoundsException e) {
            return gui.taskError("emptyTask");
        }
    }

    /**
     * Method to execute unmark.
     *
     * @param parser to parse the information.
     * @return the task unmarked.
     */
    private String executeUnmark(Parser parser) {
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
    }

    /**
     * Method to execute mark.
     *
     * @param parser to parse the information.
     * @return the marked task.
     */
    private String executeMark(Parser parser) {
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
    }

    /**
     * Method to execute list.
     *
     * @return the list of tasks.
     */
    private String executeList() {
        try {
            if (tasks.size() == 0) {
                throw new McBotException("Your list is empty boi");
            }
            assert tasks.size() != 0 : "Task size should be more than 0";
            return gui.listTask(tasks.getList());
        } catch (McBotException e) {
            return gui.printError(e);
        }
    }

    /**
     * Helper method to create event task.
     *
     * @param parser to parse information.
     * @return the event task created.
     * @throws InvalidCommandException when any data is missing.
     */
    private Event createEvent(Parser parser) throws InvalidCommandException {
        String taskName = parser.getEventTask();
        String dateStr = parser.getEventDate();
        LocalDate eventDate = LocalDate.parse(dateStr, dateFormatter);
        if (parser.isThereTime()) {
            String timeStr = parser.getEventTime();
            LocalTime eventTime = LocalTime.parse(timeStr, timeFormatter);
            return new Event(taskName, eventDate, eventTime);
        } else {
            return new Event(taskName, eventDate);
        }
    }

    /**
     * Helper method to create deadline task.
     *
     * @param parser to parse information.
     * @return the deadline task created.
     * @throws InvalidCommandException when any data is missing.
     */
    private Task createDeadline(Parser parser) throws InvalidCommandException {
        String taskName = parser.getDeadlineTask();
        String dateStr = parser.getDeadlineDate();
        LocalDate deadlineDate = LocalDate.parse(dateStr, dateFormatter);
        if (parser.isThereTime()) {
            String timeStr = parser.getDeadlineTime();
            LocalTime deadlineTime = LocalTime.parse(timeStr, timeFormatter);
            return new Deadline(taskName, deadlineDate, deadlineTime);
        } else {
            return new Deadline(taskName, deadlineDate);
        }
    }
}
