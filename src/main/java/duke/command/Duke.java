package duke.command;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Timer;
import java.util.TimerTask;

import duke.exception.IncompleteInputException;
import duke.exception.WrongInputException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.MainWindow;
import duke.ui.Parser;
import duke.ui.ResponseGenerator;
import javafx.application.Platform;

/**
 * An application to manage Tasks.
 */
public class Duke {
    static final String DEADLINE_FORMAT = "/by";
    static final String EVENT_FORMAT = "/at";

    private Parser parser;
    private Storage storage;
    private TaskList taskList;
    private ResponseGenerator responseGenerator;
    private MainWindow mainWindow;

    /**
     * Creates a new Duke instance.
     */
    public Duke() {
        responseGenerator = new ResponseGenerator();
        parser = new Parser();
        try {
            storage = new Storage();
            taskList = storage.initialize();
        } catch (IOException e) {
            System.out.println(responseGenerator.getFileErrorMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Sets the mainWindow reference this Duke instance to the
     * given mainWindow to allow for easier communication.
     *
     * @param mainWindow The GUI of Duke.
     */
    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    /**
     * Returns Duke's startup message.
     *
     * @return A startup message.
     */
    public String getStartupMessage() {
        return responseGenerator.getStartupMessage();
    }

    /**
     * Returns Duke's response based on the given input.
     *
     * @param input The given user input.
     * @return Duke's response.
     */
    public String getResponse(String input) {
        if (input.equals("bye")) {
            return responseGenerator.getByeMessage();
        } else if (input.equals("list")) {
            return responseGenerator.printItems(taskList.getItems());
        } else {
            try {
                String command = parser.parseCommand(input);
                if (command.equals("mark") || command.equals("unmark")) {
                    return modifyTask(input, command);
                } else if (command.equals("delete")) {
                    return deleteTask(input);
                } else if (command.equals("remind")) {
                    return setReminder(input);
                } else if (command.equals("find")) {
                    return findItems(input);
                } else {
                    return addTask(input, command);
                }
            } catch (WrongInputException | IncompleteInputException e) {
                return responseGenerator.getDukeErrorMessage(e);
            } catch (DateTimeParseException e) {
                return responseGenerator.getDateTimeFormatErrorMessage();
            } catch (IOException e) {
                return responseGenerator.getIoErrorMessage();
            } catch (ArithmeticException e) {
                return responseGenerator.getMaxDateTimeExceededErrorMessage();
            } catch (Exception e) {
                return e.getMessage();
            }
        }
    }

    private String modifyTask(String input, String command) throws WrongInputException, IOException {
        int index = parser.parseNumericalDescription(input, command, taskList.size());
        storage.modifyTask(index);
        if (command.equals("mark")) {
            return taskList.markItemDone(index);
        }
        return taskList.markItemUndone(index);
    }

    private String deleteTask(String input) throws WrongInputException, IOException {
        int index = parser.parseNumericalDescription(input, "delete", taskList.size());
        storage.deleteTask(index);
        return taskList.deleteItem(index);
    }

    private String setReminder(String input) throws WrongInputException {
        Task task = parser.parseReminderDescription(input, taskList);
        long timeElapsed = LocalDateTime.now().until(task.getReminder().getLocalDateTime(),
                ChronoUnit.MILLIS);
        new Timer().schedule(new TimerTask() {
            public void run() {
                Platform.runLater(() ->
                        mainWindow.showReminder(responseGenerator.getReminderMessage(task)));
            }
        }, timeElapsed);
        return responseGenerator.getAddReminderMessage(task, task.getReminder());
    }

    private String findItems(String input) throws IncompleteInputException {
        String query = parser.parseStringDescription(input, "find");
        return responseGenerator.printFoundItems(taskList.findItems(query));
    }

    private String addTask(String input, String command) throws IncompleteInputException, WrongInputException,
            IOException {
        if (command.equals("deadline")) {
            String[] description = parser.parseFormatDescription(input, "deadline", DEADLINE_FORMAT);
            taskList.addTask(new Deadline(description[0], description[1]));
        } else if (command.equals("event")) {
            String[] description = parser.parseFormatDescription(input, "event", EVENT_FORMAT);
            taskList.addTask(new Event(description[0], description[1]));
        } else {
            String description = parser.parseStringDescription(input, "todo");
            taskList.addTask(new Todo(description));
        }
        Task latestTask = taskList.getLast();
        storage.addTask(latestTask);
        return responseGenerator.getAddTaskMessage(latestTask, taskList.size());
    }
}
