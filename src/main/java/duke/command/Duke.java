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
    private Parser parser;
    private Storage storage;
    private TaskList taskList;
    private ResponseGenerator generator;

    /**
     * Creates a new Duke instance.
     */
    public Duke() {
        generator = new ResponseGenerator();
        parser = new Parser();
        try {
            storage = new Storage();
            taskList = storage.initialize();
        } catch (IOException e) {
            System.out.println(generator.getFileErrorMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Returns Duke's startup message.
     *
     * @return A startup message.
     */
    public String getStartupMessage() {
        return generator.getStartupMessage();
    }

    /**
     * Returns Duke's response based on the given input.
     *
     * @param input The given user input.
     * @return Duke's response.
     */
    public String getResponse(String input) {
        if (input.equals("bye")) {
            return generator.getByeMessage();
        } else if (input.equals("list")) {
            return generator.printItems(taskList.getItems());
        } else {
            try {
                String command = parser.parseCommand(input);

                if (command.equals("mark")) {
                    int index = parser.parseNumericalDescription(input, "mark", taskList.size());
                    storage.modifyTask(index);
                    return taskList.markItemDone(index);

                } else if (command.equals("unmark")) {
                    int index = parser.parseNumericalDescription(input, "unmark", taskList.size());
                    storage.modifyTask(index);
                    return taskList.markItemUndone(index);

                } else if (command.equals("delete")) {
                    int index = parser.parseNumericalDescription(input, "delete", taskList.size());
                    storage.deleteTask(index);
                    return taskList.deleteItem(index);

                } else if (command.equals("find")) {
                    String description = parser.parseStringDescription(input, "find");
                    return generator.printFoundItems(taskList.findItems(description));

                } else {
                    // adding new tasks
                    if (command.equals("todo")) {
                        String description = parser.parseStringDescription(input, "todo");
                        taskList.addTask(new Todo(description));

                    } else if (command.equals("deadline")) {
                        String[] params = parser.parseFormatDescription(input, "deadline", "/by");
                        taskList.addTask(new Deadline(params[0], params[1]));

                    } else if (command.equals("event")) {
                        String[] params = parser.parseFormatDescription(input, "event", "/at");
                        taskList.addTask(new Event(params[0], params[1]));
                    }

                    Task latestTask = taskList.getLast();
                    storage.addTask(latestTask);
                    return generator.getAddTaskMessage(latestTask, taskList.size());
                }
            } catch (WrongInputException | IncompleteInputException e) {
                return e.getMessage();
            } catch (DateTimeParseException e) {
                return "Error parsing date, please enter dates in YYYY-MM-DD format!";
            } catch (IOException e) {
                return generator.getIoErrorMessage();
            } catch (Exception e) {
                return e.getMessage();
            }
        }
    }
}
