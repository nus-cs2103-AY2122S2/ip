package duke;

import java.io.IOException;
import java.util.List;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;
import tasks.ToDo;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;
    private List<Task> tasks;

    /**
     * Constructor for Duke.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("Data/tasks.txt");
        try {
            taskList = new TaskList(storage, storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        } finally {
            tasks = taskList.getTasks();
            parser = new Parser(taskList);
        }
    };

    /**
     * Initialises duke.Duke the chat-bot along with its necessary classes and files.
     *
     * @param filePath Path directory of the saved file containing the list of tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage, storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Performs an action depending on user's input.
     *
     * @param str User's input after parsing.
     * @return A string describing the action taken.
     */
    public String handleCommand(String str) {
        try {
            String command = parser.parse(str);
            if (command.equals("bye")) {
                return ui.getByeMessage();
            } else if (command.equals("list")) {
                return taskList.printList();
            } else {
                String[] temp = str.split(" ");
                String firstWord = temp[0];
                switch (firstWord) {
                case "mark":
                    return markCommand(command);
                case "unmark":
                    return unmarkCommand(command);
                case "delete":
                    return deleteCommand(command);
                case "todo":
                case "event":
                case "deadline":
                    return addTaskCommand(command);
                case "find":
                    return taskList.find(str.substring(5));
                case "schedule":
                    return taskList.printSchedule(str.substring(9));
                default:
                    assert false : firstWord;
                }
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return str;
    }

    /**
     * Executes the command to mark a task as done.
     * @param command Input from user containing a specific task number to be marked.
     * @return A string detailing the task being marked as done.
     * @throws IOException
     */
    public String markCommand(String command) throws IOException {
        String[] temp = command.split(" ");
        int taskNumber = Integer.parseInt(temp[1]);
        return taskList.mark(taskNumber);
    }

    /**
     * Executes the command to mark a task as not done.
     * @param command Input from user containing a specific task number to be marked as not done.
     * @return A string detailing the task being marked as not done.
     * @throws IOException
     */
    public String unmarkCommand(String command) throws IOException {
        String[] temp = command.split(" ");
        int taskNumber = Integer.parseInt(temp[1]);
        return taskList.unmark(taskNumber);
    }

    /**
     * Executes the command to delete a task.
     * @param command Input from user containing a specific task number to be deleted.
     * @return A string detailing the task being deleted.
     * @throws IOException
     */
    public String deleteCommand(String command) throws IOException {
        String[] temp = command.split(" ");
        int taskNumber = Integer.parseInt(temp[1]);
        return taskList.delete(taskNumber);
    }

    /**
     * Performs the command to add a task to the list of tasks.
     * @param command Input from user containing the type and description of task to be added.
     * @return A string detailing the task being added to list of tasks.
     * @throws IOException
     */
    public String addTaskCommand(String command) throws IOException, DukeException {
        String[] temp = command.split(" ");
        String firstWord = temp[0];
        switch (firstWord) {
        case "todo":
            ToDo todo = new ToDo(command.substring(5));
            return taskList.addTask(todo);
        case "event":
            Event event = new Event(command.substring(6));
            return taskList.addTask(event);
        case "deadline":
            Deadline deadline = new Deadline(command.substring(9));
            return taskList.addTask(deadline);
        default:
            throw new DukeException();
        }
    }

}

