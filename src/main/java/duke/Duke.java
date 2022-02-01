package duke;

import java.util.List;

import javafx.scene.image.Image;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;
import tasks.Todo;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;
    private List<Task> tasks;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructor for Duke.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("Data/tasks.txt");
        try {
            taskList = new TaskList(storage.load());
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
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Performs an action depending on user's input.
     *
     * @param command User's input after parsing.
     * @return A string describing the action taken.
     */

    public String handleCommand(String command) {
        try {
            String str = parser.parse(command);
            switch (str) {
            case "bye":
                return ui.getByeMessage();
            case "list":
                return taskList.printList();
            default:
                String[] temp = str.split(" ");
                if (temp[0].equals("unmark") || temp[0].equals("mark") || temp[0].equals("delete")) {
                    int taskNumber = Integer.parseInt(temp[1]);
                    switch (temp[0]) {
                    case "mark":
                        Task currTask = tasks.get(taskNumber - 1);
                        currTask.setDone();
                        storage.save(taskList);
                        return "Nice! I've marked this task as done: \n" + "  " + currTask;
                    case "unmark":
                        Task t = tasks.get(taskNumber - 1);
                        t.setNotDone();
                        storage.save(taskList);
                        return "OK, I've marked this task as not done yet:: \n" + "  " + t;
                    case "delete":
                        Task task = tasks.get(taskNumber - 1);
                        tasks.remove(taskNumber - 1);
                        storage.save(taskList);
                        return "Okay, I have deleted " + task;
                    default:
                        return "Invallid command!";
                    }
                } else {
                    switch (temp[0]) {
                    case "todo":
                        Todo todo = new Todo(str.substring(5));
                        taskList.addTask(todo);
                        storage.save(taskList);
                        return "Added: " + todo;
                    case "event":
                        Event event = new Event(str.substring(6));
                        taskList.addTask(event);
                        storage.save(taskList);
                        return "Added: " + event;
                    case "deadline":
                        Deadline deadline = new Deadline(str.substring(9));
                        taskList.addTask(deadline);
                        storage.save(taskList);
                        return "Added: " + deadline;
                    case "find":
                        return taskList.find(str.substring(5));
                    default:
                    }
                }
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return command;
    }
}

