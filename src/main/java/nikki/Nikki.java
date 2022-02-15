package nikki;

import java.io.IOException;

import nikki.command.Command;
import nikki.command.CommandParser;
import nikki.task.Deadline;
import nikki.task.Event;
import nikki.task.Task;
import nikki.task.TaskList;
import nikki.task.Todo;

/**
 * Main class that abstracts the implementation of task list chatbot
 */
public class Nikki {
    /** Status of Nikki (stopped or not) */
    private boolean isStopped = false;

    /** List of Tasks */
    private TaskList tasks;

    /** Storage component for load/save Tasks upon program start/end */
    private Storage storage;

    /**
     * Parser for handling user input
     * Contains predefined command list and syntax
     */
    private final CommandParser cmd = new CommandParser();

    /** Ui component for displaying text in format and color */
    private final Ui ui = new Ui();

    /** Introduction string */
    private String introduction = "Hello, I'm Nikki\n"
            + "What can I do for you?";

    /**
     * Constructs a Nikki instance
     *
     * @param filename File to load and save Tasks
     */
    public Nikki(String filename) {
        try {
            storage = new Storage(filename);
            tasks = storage.loadTasks();
        } catch (IOException | NikkiException e) {
            tasks = new TaskList();
        }

    }

    /**
     * Responds to input passed by user to input
     *
     * @param input input from user
     * @return response from Nikki
     */
    public String interact(String input) {
        try {
            if (isStopped) {
                throw new NikkiException("I've already stopped");
            }
            Command action = cmd.parseCommand(input);
            String response = handleActionAndRespond(action);
            return response;
        } catch (NikkiException e) {
            return "!( ｀Д´)ﾉ  " + e.getLocalizedMessage();
        }
    }

    /**
     * Handles behaviours according to the command passed and responds with a message
     *
     * @param action command from user
     * @return Response message from Nikki
     * @throws NikkiException general exception for invalid user command: invalid command, arguments, etc.
     */
    private String handleActionAndRespond(Command action) throws NikkiException {
        String response = "";

        switch (action.getName()) {
        case "bye":
            response = "Bye! See you later!";
            try {
                storage.saveTasks(tasks);
            } catch (IOException e) {
                response += "Oh... I can't save your tasks to file";
            }
            isStopped = true;
            break;

        case "list":
            response = ui.getListReport(tasks);
            break;

        case "find":
            String pattern = action.getArgs();
            response = ui.getListReport(tasks.searchTasks(pattern.toLowerCase()));
            break;

        case "mark":
            // User input is 1-indexed, list uses 0-index
            int markIdx = Integer.parseInt(action.getArgs()) - 1;
            tasks.markTask(markIdx);
            response = ui.getUpdateTaskResponse(tasks.getTask(markIdx));
            break;

        case "unmark":
            // User input is 1-indexed, list uses 0-index
            int unmarkIdx = Integer.parseInt(action.getArgs()) - 1;
            tasks.unmarkTask(unmarkIdx);
            response = ui.getUpdateTaskResponse(tasks.getTask(unmarkIdx));
            break;

        case "delete":
            // User input is 1-indexed, list uses 0-index
            int dltIdx = Integer.parseInt(action.getArgs()) - 1;
            Task deletedTask = tasks.removeTask(dltIdx);
            response = ui.getDeletedTaskResponse(deletedTask);
            response += ui.getTaskCountReport(tasks.size());
            break;

        case "todo":
            String todoName = action.getArgs();
            Todo todo = new Todo(todoName);
            tasks.addTask(todo);
            response = ui.getNewTaskResponse(todo);
            response += ui.getTaskCountReport(tasks.size());
            break;

        case "deadline":
            String deadlineName = action.getArgs();
            String deadlineDate = action.getKwargs().get("by");
            Deadline deadline = new Deadline(deadlineName, deadlineDate);
            tasks.addTask(deadline);
            response = ui.getNewTaskResponse(deadline);
            response += ui.getTaskCountReport(tasks.size());
            break;

        case "event":
            String eventName = action.getArgs();
            String eventDate = action.getKwargs().get("at");
            Event event = new Event(eventName, eventDate);
            tasks.addTask(event);
            response = ui.getNewTaskResponse(event);
            response += ui.getTaskCountReport(tasks.size());
            break;

        case "clear":
            tasks.clear();
            break;

        default:
            throw new NikkiException("I don't know what to do");

        }

        // All valid command handler should give a response
        // Else, an exception should have been raised
        assert !response.isEmpty();

        return response;
    }

    /**
     * Getter for introduction string
     * @return introduction string
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * Getter for status of Nikki
     * @return boolean value for whether Nikki has stopped
     */
    public boolean isStopped() {
        return isStopped;
    }
}
