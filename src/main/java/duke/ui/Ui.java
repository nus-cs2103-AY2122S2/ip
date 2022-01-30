package duke.ui;

import duke.tasklist.TaskList;
import duke.tasks.Task;

import java.util.ArrayList;

public class Ui {
    protected static final String DIVIDER = "--------------------------------------------";

    public static void printWelcomeMessage() {
        System.out.println(DIVIDER);
        System.out.println("Hello I'm Duke! \nWhat can I do for you?");
        System.out.println(DIVIDER);
    }

    public static void printExitMessage() {
        System.out.println(DIVIDER);
        System.out.println("Bye! Have a nice day! :)");
        System.out.println(DIVIDER);
    }

    public static void printDivider() {
        System.out.println(DIVIDER);
    }

    public static void printAddSuccess(TaskList taskList) {
        int lastTaskIndex = taskList.getTasks().size() - 1;
        System.out.println("Got it. I've added this task:\n" + taskList.getTasks().get(lastTaskIndex));
        System.out.println("Now you have " + taskList.getTasks().size() + " task(s) in the list");
    }

    public static void printInvalidCommand() {
        System.out.println("Command is invalid, please try again!");
    }

    /**
     * Iterates and prints through the tasks
     *
     * @param tasks the ArrayList of tasks
     */
    public static void printTasks(ArrayList<Task> tasks) {
        int count = 1;
        for (Task task : tasks) {
            System.out.println(count + ". " + task.toString());
            count++;
        }
    }
}
