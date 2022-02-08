package duke;

import java.time.LocalDate;
import java.util.List;

import javafx.scene.image.Image;
import tasks.*;
import tasks.ToDo;

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
                String firstWord = temp[0];
                if (firstWord.equals("unmark") || firstWord.equals("mark") || firstWord.equals("delete")) {
                    int taskNumber = Integer.parseInt(temp[1]);
                    switch (firstWord) {
                    case "mark":
                        return taskList.mark(taskNumber);
                    case "unmark":
                        return taskList.unmark(taskNumber);
                    case "delete":
                        return taskList.delete(taskNumber);
                    default:
                        assert false : firstWord;
                    }
                } else {
                    switch (firstWord) {
                    case "todo":
                        ToDo todo = new ToDo(str.substring(5));
                        return taskList.addTask(todo);
                    case "event":
                        Event event = new Event(str.substring(6));
                        return taskList.addTask(event);
                    case "deadline":
                        Deadline deadline = new Deadline(str.substring(9));
                        return taskList.addTask(deadline);
                    case "find":
                        return taskList.find(str.substring(5));
                    case "schedule":
                        return taskList.printSchedule(str.substring(9));
                    default:
                        assert false : firstWord;
                    }
                }
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return command;
    }
}

