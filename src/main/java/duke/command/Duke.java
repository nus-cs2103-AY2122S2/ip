package duke.command;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.exception.IncompleteInputException;
import duke.exception.WrongInputException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.Parser;
import duke.ui.ResponseGenerator;

/** An application to manage tasks */
public class Duke {
    static final String DEADLINE_FORMAT = "/by";
    static final String EVENT_FORMAT = "/at";
    private Parser parser;
    private Storage storage;
    private TaskList taskList;
    private ResponseGenerator responseGenerator;

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
                    int index = parser.parseNumericalDescription(input, command, taskList.size());
                    storage.modifyTask(index);
                    return taskList.markItemDone(index);
                } else if (command.equals("unmark")) {
                    int index = parser.parseNumericalDescription(input, command, taskList.size());
                    storage.modifyTask(index);
                    return taskList.markItemUndone(index);
                } else if (command.equals("delete")) {
                    int index = parser.parseNumericalDescription(input, command, taskList.size());
                    storage.deleteTask(index);
                    return taskList.deleteItem(index);
                } else if (command.equals("find")) {
                    String query = parser.parseStringDescription(input, command);
                    return responseGenerator.printFoundItems(taskList.findItems(query));
                } else {
                    // adding new tasks
                    if (command.equals("todo")) {
                        String description = parser.parseStringDescription(input, command);
                        taskList.addTask(new Todo(description));
                    } else if (command.equals("deadline")) {
                        String[] description = parser.parseFormatDescription(input, command, DEADLINE_FORMAT);
                        taskList.addTask(new Deadline(description[0], description[1]));
                    } else if (command.equals("event")) {
                        String[] description = parser.parseFormatDescription(input, command, EVENT_FORMAT);
                        taskList.addTask(new Event(description[0], description[1]));
                    }
                    Task latestTask = taskList.getLast();
                    storage.addTask(latestTask);
                    return responseGenerator.getAddTaskMessage(latestTask, taskList.size());
                }
            } catch (WrongInputException | IncompleteInputException e) {
                return responseGenerator.getDukeErrorMessage(e);
            } catch (DateTimeParseException e) {
                return responseGenerator.getDateTimeFormatErrorMessage();
            } catch (IOException e) {
                return responseGenerator.getIoErrorMessage();
            } catch (Exception e) {
                return e.getMessage();
            }
        }
    }
}
