package commands;

import myTasks.Deadline;
import myTasks.Event;
import myTasks.Task;
import myTasks.Todo;

import java.util.List;

import duke.Ui;

/**
 * CreateTask class contain methods to create new tasks for the user to track.
 */
public class CreateTask {
    /**
     * Creates a task without restrictions.
     * @param description description of the task.
     * @param list list of tasks that are currently being tracked.
     */
    public static void todo(String description, List<Task> list, int taskCount) {
        list.add(new Todo(description));
        System.out.println(list.get(taskCount).toString());
        System.out.println("Got it. I've added this task:");
        Ui.printAndSave(taskCount + 1);
    }

    /**
     * Creates a task that must be completed before a certain time.
     * @param description description of the task.
     * @param list list of tasks that are currently being tracked.
     */
    public static void deadline(String description, List<Task> list, int taskCount) {
        String[] restOfPara  = description.split("/by ", 2);
        list.add(new Deadline(restOfPara[0], restOfPara[1]));
        System.out.println(list.get(taskCount).toString());
        System.out.println("Got it. I've added this task:");
        Ui.printAndSave(taskCount + 1);
    }

    /**
     * Creates a task that will begin at a certain time.
     * @param description description of the task.
     * @param list list of tasks that are currently being tracked.
     */
    public static void event(String description, List<Task> list, int taskCount) {
        String[] restOfPara2 = description.split("/at ", 2);
        list.add(new Event(restOfPara2[0], restOfPara2[1]));
        System.out.println(list.get(taskCount).toString());
        System.out.println("Got it. I've added this task:");
        Ui.printAndSave(taskCount + 1);
    }
}
