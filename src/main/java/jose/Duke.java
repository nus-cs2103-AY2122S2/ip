package jose;

import java.io.IOException;

import jose.task.Deadline;
import jose.task.Event;
import jose.task.Task;
import jose.task.ToDo;

/**
 * The main class used to run Jose. Is called Duke for consistency.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Class constructor that also sets up the task list and retrieves/creates the data file.
     *
     * @param filePath The path of the data file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
            parser = new Parser();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Returns a response based on the user's input.
     *
     * @param input User input.
     * @return Jose's response.
     */
    public String getResponse(String input) {
        Task tempTask;

        try {
            Parser.Command command = parser.parse(input);

            switch (command) {
            case BYE:
                return ui.getExitMessage();
            case LIST:
                return ui.getList(tasks.getTasks());
            case HELP:
                return ui.getHelpMessage();
            case PRIORITY:
                tempTask = tasks.getTask(parser.getIndex(input));
                tempTask.changePriority(parser.getPriority(input));
                storage.update(tasks);
                return ui.getPriorityMessage(tempTask);
            case MARK:
                tempTask = tasks.getTask(parser.getIndex(input));
                tempTask.mark();
                storage.update(tasks);
                return ui.getMarkMessage(tempTask);
            case UNMARK:
                tempTask = tasks.getTask(parser.getIndex(input));
                tempTask.unmark();
                storage.update(tasks);
                return ui.getUnmarkMessage(tempTask);
            case DELETE:
                tempTask = tasks.getTask(parser.getIndex(input));
                tasks.removeTask(tempTask);
                storage.update(tasks);
                return ui.getDeleteMessage(tempTask, tasks);
            case FIND:
                return ui.getList(tasks.findTasks(parser.getQuery(input)));
            case TODO:
                tempTask = createTodo(input);
                tasks.addTask(tempTask);
                storage.update(tasks);
                return ui.getAddMessage(tempTask, tasks);
            case DEADLINE:
                tempTask = createDeadline(input);
                tasks.addTask(tempTask);
                storage.update(tasks);
                return ui.getAddMessage(tempTask, tasks);
            case EVENT:
                tempTask = createEvent(input);
                tasks.addTask(tempTask);
                storage.update(tasks);
                return ui.getAddMessage(tempTask, tasks);
            default:
                return "Nani?! No comprende por favor. Type 'help' for help homer.";
            }
        } catch (DukeException e) {
            return "Error: " + e.getMessage();
        }
    }

    /**
     * Returns a newly created todo task based on the user's input.
     *
     * @param input User input.
     * @return A ToDo object.
     */
    public ToDo createTodo(String input) {
        String[] taskInfo = input.split(" ", 2);
        assert taskInfo.length == 2 : "taskInfo should contain exactly 2 strings";
        return new ToDo(taskInfo[1]);
    }

    /**
     * Returns a newly created deadline task.
     *
     * @param input User input.
     * @return A Deadline object.
     * @throws DukeException If date and time are in the wrong format.
     */
    public Deadline createDeadline(String input) throws DukeException {
        String[] taskInfo = input.split(" ", 2)[1].split(" /by ");

        if (taskInfo.length != 2) {
            throw new DukeException("Incorrecto format. Format: deadline [desc] /by [yyyy-MM-dd HHmm]");
        }

        return new Deadline(taskInfo[0], taskInfo[1]);
    }

    /**
     * Returns a newly created even task.
     *
     * @param input User input.
     * @return An event object.
     * @throws DukeException If date and time are in the wrong format.
     */
    public Event createEvent(String input) throws DukeException {
        String[] taskInfo = input.split(" ", 2)[1].split(" /at ");

        if (taskInfo.length != 2) {
            throw new DukeException("Incorrecto format. Format: event [desc] /at [yyyy-MM-dd HHmm]");
        }

        return new Event(taskInfo[0], taskInfo[1]);
    }
}
