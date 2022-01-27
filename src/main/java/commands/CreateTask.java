package commands;

import myTasks.Deadline;
import myTasks.Event;
import myTasks.Task;
import myTasks.Todo;

import java.util.List;

/**
 * The CreateTask class contain methods to create new tasks for the user to track.
 */
public class CreateTask {
    /**
     * The method todo creates a task without restrictions.
     * @param description contains the description of the task.
     * @param list contains the list of tasks that are currently being tracked.
     */
    public static void todo(String description, List<Task> list) {
        list.add(new Todo(description));
    }

    /**
     * The method deadline creates a task that must be completed before a certain time.
     * @param description contains the description of the task.
     * @param list contains the list of tasks that are currently being tracked.
     */
    public static void deadline(String description, List<Task> list) {
        String[] restOfPara  = description.split("/by ", 2);
        list.add(new Deadline(restOfPara[0], restOfPara[1]));
    }

    /**
     * The method event creates a task that will begin at a certain time.
     * @param description contains the description of the task.
     * @param list contains the list of tasks that are currently being tracked.
     */
    public static void event(String description, List<Task> list) {
        String[] restOfPara2 = description.split("/at ", 2);
        list.add(new Event(restOfPara2[0], restOfPara2[1]));
    }
}
