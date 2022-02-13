package duke.ui;

import java.util.ArrayList;
import java.util.List;

import duke.Duke;
import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * Controller for Window.
 */
public class Window extends BorderPane {
    @FXML
    private TextField searchTaskInput;
    @FXML
    private TextField descriptionText;
    @FXML
    private TextField dateTimeText;
    @FXML
    private TextField durationText;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox taskContainer;
    @FXML
    private ChoiceBox<String> taskTypeSelector;

    private Duke duke;

    @FXML
    private void initialize() {
        scrollPane.vvalueProperty().bind(taskContainer.heightProperty());
        taskTypeSelector.getItems().addAll("To Do", "Deadline", "Event");
        taskTypeSelector.setValue("To Do");
    }

    public void setDuke(Duke d) {
        duke = d;
        TaskCard.setDuke(d);
        update();
    }

    @FXML
    private void handleTaskInput() {
        String input = descriptionText.getText();

        if (input.isEmpty()) {
            return;
        }

        try {
            switch (taskTypeSelector.getValue()) {
            case "To Do":
                duke.addToDo(descriptionText.getText());
                break;
            case "Deadline":
                duke.addDeadline(descriptionText.getText(), dateTimeText.getText());
                break;
            case "Event":
                duke.addEvent(descriptionText.getText(), dateTimeText.getText(), durationText.getText());
                break;
            default:
                break;
            }

            descriptionText.clear();
        } catch (DukeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.toString(), ButtonType.OK);
            alert.setHeaderText("Something went wrong!");
            alert.show();
        } finally {
            dateTimeText.clear();
            durationText.clear();
            update();
        }
    }

    @FXML
    private void handleSearch() {
        update();
    }

    @FXML
    private void handleTypeSelect() {
        switch (taskTypeSelector.getValue()) {
        case "To Do":
            dateTimeText.setVisible(false);
            durationText.setVisible(false);
            break;
        case "Deadline":
            dateTimeText.setVisible(true);
            durationText.setVisible(false);
            break;
        case "Event":
            dateTimeText.setVisible(true);
            durationText.setVisible(true);
            break;
        default:
            break;
        }
    }

    /**
     * Updates the Tasks displayed in the Window.
     */
    public void update() {
        taskContainer.getChildren().clear();
        TaskList filteredTasks = new TaskList();
        List<Integer> filteredTaskIndices = new ArrayList<>();
        String searchText = searchTaskInput.getText();

        for (int i = 0; i < duke.getTasks().size(); i++) {
            Task task = duke.getTasks().get(i);
            if (task.getDescription().contains(searchText)) {
                filteredTasks.add(task);
                filteredTaskIndices.add(i);
            }
        }

        assert filteredTasks.size() == filteredTaskIndices.size();

        for (int i = 0; i < filteredTasks.size(); i++) {
            taskContainer.getChildren().add(new TaskCard(this, filteredTasks.get(i), filteredTaskIndices.get(i)));
        }

        assert taskContainer.getChildren().size() == filteredTasks.size();
    }
}
