package duke;

import duke.command.Command;
import duke.managers.FileManager;
import duke.managers.TaskList;
import javafx.scene.image.Image;

public class Duke {
    private TaskList userTaskList;
    private FileManager fileManager;
    private Ui ui;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public Duke() {

    }

    /**
     * Duke Constructor
     *
     * @param savedTasksPath final path of saved tasks file
     */
    public Duke(String savedTasksPath) throws DukeException {
        this.ui = new Ui();
        this.fileManager = new FileManager(savedTasksPath);
        this.userTaskList = this.fileManager.loadTasks();
    }

    /**
     * Main method which runs duke
     *
     * @param args
     * @throws DukeException
     */
    public static void main(String[] args) throws DukeException {
        Duke duke = new Duke("./data/duke.txt");
        // duke.run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String userTaskString) {
        String dukeResponse = null;
        try {
            Parser parser = new Parser();
            Command c = parser.parseUserCommand(userTaskString);
            c.executeTask(this.userTaskList, this.fileManager);
            dukeResponse = this.userTaskList.getUi().returnResponse();
        } catch (DukeException exception) {
            return exception.toString();
        }
        return dukeResponse;
    }
}
