package duke.reminder;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.function.Predicate;

import duke.task.Task;

public class ReminderPredicates {
    static final Predicate<Task> TODAY = task -> task.getDate() != null && task.getDate().isEqual(LocalDate.now());
    static final Predicate<Task> COMPLETED = task -> (task.getStatusIcon() == "X");
    static final Predicate<Task> DUE_NOW = TODAY.and(COMPLETED.negate());
    static final Predicate<Task> OVERDUE = task -> task.getDate() != null && task.getDate().isBefore(LocalDate.now());
    static final Predicate<Task> UPCOMING = task -> task.getDate() != null && (ChronoUnit.DAYS.between(LocalDate.now(), task.getDate())) == 1;
    static final Predicate<Task> OVERDUE_AND_UPCOMING = OVERDUE.or(UPCOMING);
}
