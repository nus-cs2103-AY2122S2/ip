package duke.ui;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import duke.task.Task;
import duke.task.TaskList;

public class Ui {
    public static final String DIVIDER = "    ____________________________________________________________";
    public static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter
            .ofPattern("HH:mm EEEE, MM-dd-yyyy").localizedBy(Locale.ENGLISH);
    public static final String BOT_NAME = "duke";

    /**
     * Instantiates an empty Ui object.
     */
    public Ui() {

    }

    /**
     * Prints the UI for the start of the program.
     */
    public void showUiForStart() {
        printDivider();
        System.out.println("    Hello, I'm " + BOT_NAME + ".");
        System.out.println("    What can I do for you?");
        printDivider();
    }

    /**
     * Prints the UI for the end of the program.
     */
    public void showUiForBye() {
        printDivider();
        System.out.println("    Bye. Hope to see you again soon!");
        printDivider();
    }

    /**
     * Prints the divider.
     */
    public static void printDivider() {
        System.out.println(DIVIDER);
    }

    /**
     * Prints out every Task in the task list.
     *
     * @param taskList TaskList to be printed out.
     */
    public void showUiForTaskList(TaskList taskList) {
        printDivider();
        ArrayList<Task> tasks = taskList.getTasks();
        for (int index = 0; index < tasks.size(); index++) {
            int order = (index + 1);
            Task task = tasks.get(index);
            String result = "    " + order + ": " + task.toString();
            System.out.println(result);
        }
        printDivider();
    }

    /**
     * Prints the UI for adding a task.
     *
     * @param taskObj Task.
     * @param listLength int Length of the task list.
     */
    public void showUiForAdd(Task taskObj, int listLength) {
        printDivider();
        System.out.println("    Adding a task: ");
        System.out.println("      " + taskObj.toString());
        System.out.println("    Now you got " + (listLength) + " tasks in the list!");
        printDivider();
    }

    /**
     * Prints the UI for deleting a task.
     *
     * @param taskObj Task.
     * @param listLength int Length of the task list.
     */
    public void showUiForDelete(Task taskObj, int listLength) {
        printDivider();
        System.out.println("    Ok, removing a task: ");
        System.out.println("      " + taskObj.toString());
        System.out.println("    Now you got " + (listLength) + " tasks in the list!");
        printDivider();
    }

    /**
     * Prints all elements in the sorted list.
     *
     * @param tasks ArrayList of Task objects to be printed.
     * @param sortType Type of sorting based on the enum.
     */
    public void showUiForSort(ArrayList<Task> tasks, TaskList.SortType sortType) {
        printDivider();
        System.out.println("    Sorting your tasks by " + sortType.toString() + ":");
        for (Task task : tasks) {
            System.out.println("    " + task.toString());
        }
        printDivider();
    }
}
