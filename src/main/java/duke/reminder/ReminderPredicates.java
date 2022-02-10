package duke.reminder;

import duke.task.Task;

import java.time.LocalDate;
import java.util.function.Predicate;

public class ReminderPredicates {
    static Predicate<Task> TODAY = task -> task.getDate() != null && task.getDate().isEqual(LocalDate.now());
    static Predicate<Task> COMPLETED = task -> (task.getStatusIcon() == "X");
    static Predicate<Task> DUE_NOW = TODAY.and(COMPLETED.negate());
}
