package duke;

import java.time.LocalDateTime;
import java.util.Comparator;

/**
 * Compares Tasks to determine sort order
 */
public class TaskComparator implements Comparator<Task> {
    @Override
    public int compare(Task task, Task otherTask) {
        LocalDateTime taskDatetime = null;
        LocalDateTime otherTaskDatetime = null;
        // Obtaining DateTime of task
        if (task instanceof Deadline) {
            taskDatetime = ((Deadline) task).getDatetime();
        }
        if (task instanceof Event) {
            taskDatetime = ((Event) task).getDatetime();
        }
        // Obtaining DateTime of otherTask
        if (otherTask instanceof Deadline) {
            otherTaskDatetime = ((Deadline) otherTask).getDatetime();
        }
        if (otherTask instanceof Event) {
            otherTaskDatetime = ((Event) otherTask).getDatetime();
        }
        // If both have a DateTime, order by DateTime
        if (taskDatetime != null && otherTaskDatetime != null) {
            return taskDatetime.compareTo(otherTaskDatetime);
        }
        // Always put Deadline and Event Tasks in front of ToDo task
        if ((task instanceof Deadline || task instanceof Event) && otherTask instanceof ToDo) {
            return -1;
        }
        if ((otherTask instanceof Deadline || otherTask instanceof Event) && task instanceof ToDo) {
            return 1;
        }
        // Compare by Description
        return task.getDetails()[2].compareTo(otherTask.getDetails()[2]);
    }
}
