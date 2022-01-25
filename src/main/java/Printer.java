import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class Printer {
    public static String divider = "    ____________________________________________________________";
    public static DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("HH:mm EEEE, MM-dd-yyyy").localizedBy(Locale.ENGLISH);

    public static void printEndMessage() {
        printDivider();
        System.out.println("    Bye. Hope to see you again soon!");
        printDivider();
    }

    public static void printDivider() {
        System.out.println(divider);
    }

    public static void printTodo(ArrayList<Task> inputList) {
        printDivider();
        for (int index = 0; index < inputList.size(); index++) {
            int order = (index + 1);
            Task task = inputList.get(index);
            String result = "    " + order + ": " + task.toString();
            System.out.println(result);
        }
        printDivider();
    }

    public static void echoForAdd(Task taskObj, int listLength) {
        printDivider();
        System.out.println("    Adding a task: ");
        System.out.println("      " + taskObj.toString());
        System.out.println("    Now you got " + (listLength) + " tasks in the list!");
        printDivider();
    }

    public static void echoForDelete(Task taskObj, int listLength) {
        printDivider();
        System.out.println("    Ok, removing a task: ");
        System.out.println("      " + taskObj.toString());
        System.out.println("    Now you got " + (listLength) + " tasks in the list!");
        printDivider();
    }

    public static void echoForSort(ArrayList<Task> tasks, Duke.SortType sortType) {
        printDivider();
        System.out.println("    Sorting your tasks by " + sortType.toString() + ":");
        for (Task task : tasks) {
            System.out.println("    " + task.toString());
        }
        printDivider();
    }
}
