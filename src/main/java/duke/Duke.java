package duke;

import java.util.List;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.Parser;
import duke.exception.DukeException;
import duke.task.Task;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * Main Duke class that runs the task management program, Duke.
 */
public class Duke extends Application {
    private static final String FILE_PATH = "./data/test.txt";

    private static final Ui UI = new Ui();

    private List<Task> taskList = Storage.loadFromFile(FILE_PATH);

    public Ui getUi() {
        return UI;
    }

    @Override
    public void start (Stage stage) {
        try {
            UI.buildStage(stage);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            Platform.exit();
        }
        stage.show();
    }

    public String getResponse(String userInput) {
        try {
            Command c = Parser.parse(userInput);
            if (c instanceof ByeCommand) {
                Platform.exit();
            }
            return c.execute(taskList, UI);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
