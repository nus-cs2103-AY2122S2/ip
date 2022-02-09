package seedu.duke;

import seedu.command.*;

import java.io.IOException;

/**
 * Includes the Main driver class.
 * Stores public static instances of storage, task and ui objects.
 * Contains methods to run and instantiate the object instances.
 * Handles the result from the parsed commands to print out different
 * results back to the user.
 */
public class Duke {
    private static Storage storage;
    private static TaskList tasks;
    //private Ui ui;
    private String FILE_PATH = "taskHistory.txt";

    /**
     * Instantiates the ui, storage and tasklist objects.
     * Stores the tasks loaded into the tasklist object.
     *
     */
    public Duke() {
        //ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            //ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Shows the welcome line when Duke chat-bot starts running.
     * Interacts with user inputs by calling getNextInput() from ui class.
     * Calls Parser class methods to parse in the user inputs.
     * Switches between cases based on the command passed in.
     * Exits the running chat-bot when the 'bye' command is read from user input.
     */
    public String getResponse(String input) {
        String nextInput = input;
        String command = Parser.getCommand(nextInput);
        try {
            switch (command) {
            case "hi":
                return WelcomeCommand.run();
            case "bye":
                return ExitCommand.run();
            case "list":
                try {
                    return ListCommand.run(tasks);
                } catch (IOException e) {
                    return e.getMessage();
                }
            case "deadline":
                if (Parser.getLength(nextInput) == 1) {
                    throw new DukeException("The description of a deadline cannot be empty.");
                } else {
                    String[] task = Parser.getDeadlineDetails(nextInput);
                    Deadline d = new Deadline(task[0], task[1]);
                    tasks.add(d);
                    try {
                        return AddCommand.run(d, tasks, storage);
                    } catch (IOException exception) {
                        return exception.getMessage();
                    }
                }
            case "todo":
                if (Parser.getLength(nextInput) == 1) {
                    throw new DukeException("The description of a todo cannot be empty.");
                } else {
                    String task = Parser.getTodoDetails(nextInput);
                    ToDo t = new ToDo(task);
                    tasks.add(t);
                    try {
                        return AddCommand.run(t, tasks, storage);
                    } catch (IOException exception) {
                        return exception.getMessage();
                    }
                }
            case "event":
                if (Parser.getLength(nextInput) == 1) {
                    throw new DukeException("The description of an event cannot be empty.");
                } else {
                    String[] task = Parser.getEventDetails(nextInput);
                    Event e = new Event(task[0], task[1]);
                    tasks.add(e);
                    try {
                        return AddCommand.run(e, tasks, storage);
                    } catch (IOException exception) {
                        return exception.getMessage();
                    }
                }
            case "mark":
                if (Parser.getLength(nextInput) == 1) {
                    throw new DukeException("You did not provide a task to mark.");
                } else {
                    int taskId = Parser.getTaskId(nextInput);
                    try {
                        return MarkCommand.run(taskId, tasks, storage);
                    } catch (IOException exception) {
                        return exception.getMessage();
                    }
                }
            case "unmark":
                if (Parser.getLength(nextInput) == 1) {
                    throw new DukeException("You did not provide a task to unmark.");
                } else {
                    int taskId = Parser.getTaskId(nextInput);
                    try {
                        return UnmarkCommand.run(taskId, tasks, storage);
                    } catch (IOException exception) {
                        return exception.getMessage();
                    }
                }
            case "delete":
                if (Parser.getLength(nextInput) == 1) {
                    throw new DukeException("You did not provide a task to delete.");
                } else {
                    int taskId = Parser.getTaskId(nextInput);
                    try {
                        return DeleteCommand.run(taskId, tasks, storage);
                    } catch (IOException exception) {
                        return exception.getMessage();
                    }
                }
            case "find":
                if (Parser.getLength(nextInput) == 1) {
                    throw new DukeException("You did not provide a keyword to search.");
                } else {
                    String keyword = Parser.getKeyword(nextInput);
                    try {
                        return FindCommand.run(keyword, tasks);
                    } catch (IOException exception) {
                        return exception.getMessage();
                    }
                }
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException error) {
            return "OOPS!!! " + error.getMessage();
        }
    }
}
