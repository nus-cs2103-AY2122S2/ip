import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class Ui {
    public static String divider = "    ____________________________________________________________";
    public static DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("HH:mm EEEE, MM-dd-yyyy").localizedBy(Locale.ENGLISH);

    public Ui() {

    }

    public void showUiForBye() {
        printDivider();
        System.out.println("    Bye. Hope to see you again soon!");
        printDivider();
    }

    public static void printDivider() {
        System.out.println(divider);
    }

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

    public void showUiForAdd(Task taskObj, int listLength) {
        printDivider();
        System.out.println("    Adding a task: ");
        System.out.println("      " + taskObj.toString());
        System.out.println("    Now you got " + (listLength) + " tasks in the list!");
        printDivider();
    }

    public void showUiForDelete(Task taskObj, int listLength) {
        printDivider();
        System.out.println("    Ok, removing a task: ");
        System.out.println("      " + taskObj.toString());
        System.out.println("    Now you got " + (listLength) + " tasks in the list!");
        printDivider();
    }

    public void showUiForSort(ArrayList<Task> tasks, TaskList.SortType sortType) {
        printDivider();
        System.out.println("    Sorting your tasks by " + sortType.toString() + ":");
        for (Task task : tasks) {
            System.out.println("    " + task.toString());
        }
        printDivider();
    }
}
