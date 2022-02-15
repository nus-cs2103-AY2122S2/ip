package duke.ui;

import java.io.IOException;
import java.util.function.Consumer;

import duke.Duke;
import duke.DukeException;
import duke.Parser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * Represents a card consisting of a Text containing the description of the Task,
 * a RadioButton to toggle the Task's status, and a Button to delete the Task.
 */
public class TaskCard extends HBox {
    private static Duke duke;

    @FXML
    private Button deleteButton;
    @FXML
    private RadioButton markButton;
    @FXML
    private Text taskText;

    private final Task task;
    private final Window window;
    private final int index;
    private final ContextMenu contextMenu = new ContextMenu();

    /**
     * Constructs a TaskCard instance.
     *
     * @param window The window that this TaskCard belongs to.
     * @param task   The Task to be displayed.
     * @param index  The index of the Task.
     */
    public TaskCard(Window window, Task task, int index) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Window.class.getResource("/view/TaskCard.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.window = window;
        this.task = task;
        this.index = index;

        taskText.setText(task.toString());
        taskText.setStrikethrough(task.isDone());
        markButton.setSelected(task.isDone());
    }

    public static void setDuke(Duke duke) {
        TaskCard.duke = duke;
    }

    /**
     * Deletes the Task shown in this card.
     */
    @FXML
    private void handleDelete() {
        duke.deleteTask(index);
        window.update();
    }

    /**
     * Marks the Task shown in this card as done or not done.
     */
    @FXML
    private void handleMark() {
        if (markButton.isSelected()) {
            duke.markTask(index);
        } else {
            duke.unmarkTask(index);
        }

        taskText.setText(task.toString());
        taskText.setStrikethrough(task.isDone());
    }

    @FXML
    private void handleClick(MouseEvent event) {
        contextMenu.hide();
        contextMenu.getItems().clear();

        if (event.getButton() != MouseButton.SECONDARY) {
            return;
        }

        EditTaskMenuItem editDescriptionItem = new EditTaskMenuItem("description", (result) -> {
            task.setDescription(result);
            window.update();
        });
        contextMenu.getItems().add(editDescriptionItem);

        if (task.getClass() == Deadline.class) {
            setUpDeadlineMenu((Deadline) task);
        }

        if (task.getClass() == Event.class) {
            setUpEventMenu((Event) task);
        }

        contextMenu.show(this, event.getScreenX(), event.getScreenY());

    }

    private void setUpDeadlineMenu(Deadline deadlineTask) {
        EditTaskMenuItem editTimeItem = new EditTaskMenuItem("time", (result) -> {
            deadlineTask.setBy(Parser.parseDateTime(result));
            window.update();
        });

        contextMenu.getItems().add(editTimeItem);
    }

    private void setUpEventMenu(Event eventTask) {
        EditTaskMenuItem editTimeItem = new EditTaskMenuItem("time", (result) -> {
            eventTask.setAt(Parser.parseDateTime(result));
            window.update();
        });

        EditTaskMenuItem editDurationItem = new EditTaskMenuItem("duration", (result) -> {
            eventTask.setDuration(Parser.parseDuration(result));
            window.update();
        });

        contextMenu.getItems().addAll(editTimeItem, editDurationItem);
    }

    /**
     * Represents a menu item in the context menu of a TaskCard.
     * Displays an EditTaskDialog on click and performs an action when a result is entered by the user.
     * If an invalid result is entered, an Alert will be displayed.
     */
    private static class EditTaskMenuItem extends MenuItem {
        /**
         * Constructs an EditTaskMenuItem instance.
         *
         * @param field  The name of the field to be edited.
         * @param action The action to be performed when the item is clicked.
         */
        public EditTaskMenuItem(String field, Consumer<String> action) {
            super("Edit " + field);
            EditTaskDialog dialog = new EditTaskDialog(field);
            setOnAction(e -> dialog.showAndWait().ifPresent(new EditTaskAction(action)));
        }
    }

    /**
     * Represents a dialog which allows the user to edit a Task.
     */
    private static class EditTaskDialog extends TextInputDialog {
        /**
         * Constructs an EditTaskDialog instance.
         *
         * @param field The name of the field to be edited.
         */
        public EditTaskDialog(String field) {
            setTitle("");
            setHeaderText("");
            setContentText("Enter new " + field + ":");
        }
    }

    /**
     * Wraps around a Consumer to be used as an action when a result is received by an EditTaskDialog.
     * If the result is empty, the action will not be performed.
     * If a DukeException is thrown while executing the action, an Alert will be displayed.
     * Saves the state of Duke after the action is performed.
     */
    private static class EditTaskAction implements Consumer<String> {
        private final Consumer<String> action;

        public EditTaskAction(Consumer<String> action) {
            this.action = action;
        }

        @Override
        public void accept(String result) {
            if (result.isEmpty()) {
                return;
            }

            try {
                action.accept(result);
                duke.saveData();
            } catch (DukeException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.toString(), ButtonType.OK);
                alert.setHeaderText("Something went wrong!");
                alert.show();
            }
        }
    }
}
