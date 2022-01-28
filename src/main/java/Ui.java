import TaskList.TaskList;
import tasks.Task;

import java.util.ArrayList;

public class Ui {
    protected static final String DIVIDER = "--------------------------------------------";

    public void printWelcomeMessage() {
        System.out.println("Hello I'm Duke\nWhat can I do for you?");
        System.out.println(DIVIDER);
    }

    public void printExitMessage() {
        System.out.println("Bye! Have a nice day.");
        System.out.println(DIVIDER);
    }

    public void printDivider() {
        System.out.println(DIVIDER);
    }

    public static void printAddSuccess(TaskList taskList) {
        int lastTaskIndex = taskList.getTasks().size() - 1;
        System.out.println("Got it. I've added this task:\n" + taskList.getTasks().get(lastTaskIndex));
        System.out.println("Now you have " + taskList.getTasks().size() + " tasks in the list");
    }

    public void printInvalidCommand() {
        System.out.println("User command is not valid, please try again!");
    }

    public static void printNoMatchTask() {
        System.out.println("No tasks found with given keyword. Please try again!");
    }

    public static void printTasks(ArrayList<Task> tasks) {
        int currentIndex = 1;
        for (Task task : tasks) {
            System.out.println(currentIndex + ". " + task.toString());
            currentIndex++;
        }
    }
}
