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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        Task tempTask;

        try {
            Parser.Command command = parser.parse(input);

            switch (command) {
            case BYE:
                return ui.showExitMessage();
            case LIST:
                return ui.showList(tasks.getTasks());
            case MARK:
                tempTask = tasks.getTask(parser.getIndex(input));
                tempTask.mark();
                updateDataFile();
                return ui.showMarkMessage(tempTask);
            case UNMARK:
                tempTask = tasks.getTask(parser.getIndex(input));
                tempTask.unmark();
                updateDataFile();
                return ui.showUnmarkMessage(tempTask);
            case DELETE:
                tempTask = tasks.getTask(parser.getIndex(input));
                ui.showDeleteMessage(tempTask);
                tasks.removeTask(tempTask);
                updateDataFile();
                return ui.showDeleteMessage(tempTask) + ui.showRemainingTasks(tasks);
            case FIND:
                return ui.showList(tasks.findTasks(input.split(" ", 2)[1]));
            case TODO:
                tempTask = createTodo(input);
                tasks.addTask(tempTask);
                updateDataFile();
                return ui.showAddMessage(tempTask) + ui.showRemainingTasks(tasks);
            case DEADLINE:
                tempTask = createDeadline(input);
                tasks.addTask(tempTask);
                updateDataFile();
                return ui.showAddMessage(tempTask) + ui.showRemainingTasks(tasks);
            case EVENT:
                tempTask = createEvent(input);
                tasks.addTask(tempTask);
                updateDataFile();
                return ui.showAddMessage(tempTask) + ui.showRemainingTasks(tasks);
            default:
                return "?";
            }
        } catch (DukeException e) {
            return "Error:" + e.getMessage();
        } catch (IOException e) {
            return "Error:" + e.getMessage();
        }
    }

    public void updateDataFile() throws IOException {
        storage.update(tasks);
    }

    public Task createTodo(String input) {
        String taskInfo[] = input.split(" ", 2);
        assert taskInfo.length == 2 : "taskInfo should contain exactly 2 strings";
        return new ToDo(taskInfo[1]);
    }

    public Task createDeadline(String input) {
        String[] taskInfo = input.split(" ", 2)[1].split(" /by ");
        assert taskInfo.length == 2 : "taskInfo should contain exactly 2 strings";
        return new Deadline(taskInfo[0], taskInfo[1]);
    }

    public Task createEvent(String input) {
        String[] taskInfo = input.split(" ", 2)[1].split(" /at ");
        assert taskInfo.length == 2 : "taskInfo should contain exactly 2 strings";
        return new Event(taskInfo[0], taskInfo[1]);
    }
}
