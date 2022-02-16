package commands;

import java.util.List;

import mytasks.Deadline;
import mytasks.Event;
import mytasks.Task;
import mytasks.Todo;

/**
 * CreateTask class contain methods to create new tasks for the user to track.
 */
public class CreateTask {
    /**
     * Creates a task without restrictions.
     * @param description description of the task.
     * @param list list of tasks that are currently being tracked.
     */
    public static String todo(String description, List<Task> list, int taskCount) {
        StringBuilder sb = new StringBuilder();
        list.add(new Todo(description));
        sb.append(list.get(taskCount).toString() + "\n");
        sb.append("Got it. I've added this task:\n");
        return sb.toString();
    }

    /**
     * Creates a task that must be completed before a certain time.
     * @param description description of the task.
     * @param list list of tasks that are currently being tracked.
     */
    public static String deadline(String description, List<Task> list, int taskCount) {
        StringBuilder sb = new StringBuilder();
        String[] restOfPara = description.split("/by ", 2);
        list.add(new Deadline(restOfPara[0], restOfPara[1]));
        sb.append(list.get(taskCount).toString() + "\n");
        sb.append("Got it. I've added this task:\n");
        return sb.toString();
    }

    /**
     * Creates a task that will begin at a certain time.
     * @param description description of the task.
     * @param list list of tasks that are currently being tracked.
     */
    public static String event(String description, List<Task> list, int taskCount) {
        StringBuilder sb = new StringBuilder();
        String[] restOfPara2 = description.split("/at ", 2);
        list.add(new Event(restOfPara2[0], restOfPara2[1]));
        sb.append(list.get(taskCount).toString() + "\n");
        sb.append("Got it. I've added this task:\n");
        return sb.toString();
    }
}
