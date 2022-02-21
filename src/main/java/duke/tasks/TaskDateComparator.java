package duke.tasks;

import duke.exceptions.DukeUnsupportedOperationException;

import java.time.LocalDateTime;
import java.util.Comparator;

public class TaskDateComparator implements Comparator<Task> {

    public int compare(Task task1, Task task2) {
        LocalDateTime task1Date = task1.getDate();
        LocalDateTime task2Date = task2.getDate();

        if (task1Date.isEqual(task2Date)) {
            return 0;
        } else if (task1Date.isBefore(task2Date)) {
            return -1;
        } else {
            return 1;
        }
    }
}
