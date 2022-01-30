package duke;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;
import tasks.Todo;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;
    List<Task> tasks;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));


    public Duke() {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage("Data/tasks.txt");
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
        finally {
            tasks = taskList.getTasks();
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

   /* public static void main(String[] args) throws DukeException, IOException {
        new Duke("Data/tasks.txt").run();
    }*/

    public String handleCommand(String command) {
        try {
            String str = parser.parse(command);
            if (str.equals("bye")){
                return str;
            }
            else if (str.equals("list")) {
                String list = "";
                for (Task t : tasks) {
                    list = list + t + "\n";
                }
                return list;
            }
            else if (str.equals("find")) {
                String s = "";
                for (Task t : taskList.getTasks()) {
                    if (t.toString().contains(str.substring(5))) {
                        s = s + t;
                    }
                }
                return s;
            } else {
                String[] temp = str.split(" ");
                if (temp[0].equals("unmark") || temp[0].equals("mark") || temp[0].equals("delete")) {
                    int taskNumber = Integer.parseInt(temp[1]);
                    if (taskList.getTasks().size() < taskNumber) {
                        return "Invalid Task number!";
                    } else {
                        if (temp[0].equals("mark")) {
                            Task currTask = tasks.get(taskNumber - 1);
                            currTask.setDone();
                            storage.save(taskList);
                            return "Nice! I've marked this task as done: \n" + "  " + currTask;
                        } else if (temp[0].equals("delete")) {
                            int index = Integer.parseInt(str.substring(7));
                            Task task = tasks.get(index - 1);
                            tasks.remove(index - 1);
                            storage.save(taskList);
                            return "Okay, I have deleted " + task;
                        } else {
                            Task currTask = tasks.get(taskNumber - 1);
                            currTask.setNotDone();
                            storage.save(taskList);
                            return "OK, I've marked this task as not done yet:: \n" + "  " + currTask;
                        }
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
                    default:

                    }
                }
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return command;
    }
    /**
     * Runs duke.Duke the chatbot and interacts with the user based on user input.
     *
     * @throws DukeException
     * @throws IOException
     */
    public String getResponse(String input) {
        return input;
    }
}

