package duke;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
/**
 * Class containing Duke and main function
 */
public class Duke extends Application {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath, String fileDirectory) {
        this.ui = new Ui();
        this.storage = new Storage(filePath, fileDirectory);
        this.taskList = new TaskList();
        this.parser = new Parser();
    }

    public void run() {
        ui.showHi();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/tasks.txt", "./data").run();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}