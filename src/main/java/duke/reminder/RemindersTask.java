package duke.reminder;

import duke.task.Task;
import duke.task.TaskList;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RemindersTask extends TimerTask {
    private Predicate<Task> predicate;

    public void run() {
        Platform.runLater(() -> {
            predicate = ReminderPredicates.TODAYS;
            List<Task> dueRems = getDueRems();
            showNotifications(dueRems); // shows the multiple notifications in an alert dialog
            //predicate = task -> (task.getStatusIcon() == "X");
        });
    }

    private List<Task> getDueRems() {
        ArrayList<Task> tasksWithDueDates = TaskList.getTasks();
        ObservableList<Task> reminders = FXCollections.observableList(tasksWithDueDates);
        return reminders.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    private void showNotifications(List<Task> listOfDueTasks) {
        for (Task t: listOfDueTasks) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Reminder!");
            alert.setContentText("Info: " + t.getDescription());
            alert.setHeaderText("Task reminder alert");
            alert.showAndWait();
        }
    }

    //private void
}

