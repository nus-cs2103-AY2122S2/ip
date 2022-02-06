package duke.main;

import duke.task.TaskList;
import duke.exception.DukeException;
import duke.command.Command;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;

/**
 * Represents Duke main class
 */
public class Duke {

    private VBox dialogContainer;
    private Storage storage;
    private TaskList tasks;
    protected Ui ui;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private String filePath = "data/tasks.txt";
    private boolean isExit = false;

    public Duke() {

        this.ui = new Ui();
        this.storage = new Storage(filePath);

        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(ui.toString(), duke)
            );
            this.tasks = new TaskList();
        }

    }

    protected String getResponse(String input) {
        if (isExit) {
            return "";
        } else {
            try {
                Command command = Parser.parse(input);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                return ui.toString();
            }
        }
    }
}
