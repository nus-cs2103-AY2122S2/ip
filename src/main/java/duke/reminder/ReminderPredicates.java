package duke.reminder;

import duke.task.Task;

import java.util.function.Predicate;

public class ReminderPredicates {
    //static Predicate<Task> TODAYS = task -> task.getDate().isEqual(LocalDate.now());
    static Predicate<Task> TODAYS = task -> (task.getStatusIcon() == "X");
    static Predicate<Task> COMPLETED = task -> (task.getStatusIcon() == "X");
    static Predicate<Task> DUE_NOW = TODAYS.and(COMPLETED.negate());
}
