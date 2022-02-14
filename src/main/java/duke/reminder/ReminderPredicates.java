package duke.reminder;

import java.time.LocalDate;
import java.util.function.Predicate;

import duke.task.Task;

public class ReminderPredicates {
    static final Predicate<Task> TODAY = task -> task.getDate() != null && task.getDate().isEqual(LocalDate.now());
    static final Predicate<Task> COMPLETED = task -> (task.getStatusIcon() == "X");
    static final Predicate<Task> DUE_NOW = TODAY.and(COMPLETED.negate());
    static final Predicate<Task> OVERDUE = task -> task.getDate() != null && task.getDate().isBefore(LocalDate.now());
}
