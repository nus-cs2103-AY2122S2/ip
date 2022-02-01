package duke.ui;

import java.io.IOException;

import duke.Duke;
import duke.task.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class TaskCard extends HBox {
    private final Duke duke;
    private final Task task;
    private final Window window;
    private final int index;
    @FXML
    private Button deleteButton;
    @FXML
    private RadioButton markButton;
    @FXML
    private Text taskText;

    public TaskCard(Window window, Duke duke, Task task, int index) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Window.class.getResource("/view/TaskCard.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.window = window;
        this.duke = duke;
        this.task = task;
        this.index = index;

        taskText.setText(task.toString());
        taskText.setStrikethrough(task.isDone());
        markButton.setSelected(task.isDone());
    }

    @FXML
    public void handleDelete() {
        duke.deleteTask(index);
        window.update();
    }

    @FXML
    public void handleMark() {
        if (markButton.isSelected()) {
            duke.markTask(index);
        } else {
            duke.unmarkTask(index);
        }

        taskText.setText(task.toString());
        taskText.setStrikethrough(task.isDone());
    }
}
