package duke.reminder;

import duke.task.Task;
import duke.task.TaskList;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RemindersTask extends TimerTask {
    private Predicate<Task> predicate;
    private String tasks = new String("");

    public void run() {
        Platform.runLater(() -> {
            setTypeOfReminder(ReminderPredicates.TODAY);
            List<Task> dueRems = getDueTasks();
            showNotifications(dueRems); // shows the multiple notifications in an alert dialog
        });
    }

    private void setTypeOfReminder(Predicate<Task> predicateTask) {
        predicate = predicateTask;
    }

    private List<Task> getDueTasks() {
        ArrayList<Task> tasksArrayList = TaskList.getTasks();
        ObservableList<Task> remindersObsList = FXCollections.observableList(tasksArrayList);
        return remindersObsList.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    private void showNotifications(List<Task> listOfDueTasks) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Reminder!");
        for (Task t: listOfDueTasks) {
            tasks += t.getDescription() + "\n";
        }
        alert.setContentText(tasks);
        if (listOfDueTasks.size() > 1) {
            alert.setHeaderText("Reminder on these tasks");
        } else {
            alert.setHeaderText("Reminder on this task");
        }
        alert.showAndWait();
    }

}

