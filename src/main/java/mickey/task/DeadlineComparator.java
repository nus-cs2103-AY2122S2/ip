package mickey.task;

import java.util.Comparator;

/**
 * Deadline comparator to compare deadlines between tasks.
 */
public class DeadlineComparator implements Comparator<Task> {
    @Override
    public int compare(Task o1, Task o2) {
        if (o1 instanceof ToDo) {
            return -1;
        } else if (o2 instanceof ToDo) {
            return 1;
        } else {
            if (o1 instanceof Deadline) {
                Deadline d1 = (Deadline) o1;
                if (o2 instanceof Deadline) {
                    Deadline d2 = (Deadline) o2;
                    return d1.getBy().compareTo(d2.getBy());
                } else {
                    Event d2 = (Event) o2;
                    return d1.getBy().compareTo(d2.getAt());
                }
            } else {
                Event e1 = (Event) o1;
                if (o2 instanceof Deadline) {
                    Deadline d2 = (Deadline) o2;
                    return e1.getAt().compareTo(d2.getBy());
                } else {
                    Event d2 = (Event) o2;
                    return e1.getAt().compareTo(d2.getAt());
                }
            }
        }
    }
}
