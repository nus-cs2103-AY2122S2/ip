import java.util.ArrayList;

public class Printer {
    public static void printEndMessage() {
        printDivider();
        System.out.println("    Bye. Hope to see you again soon!");
        printDivider();
    }

    public static void printDivider() {
        String divider = "    ____________________________________________________________";
        System.out.println(divider);
    }

    public static void printTodo(ArrayList<TodoObject> inputArray) {
        printDivider();
        for (int index = 0; index < inputArray.size(); index++) {
            int order = (index + 1);
            TodoObject todo = inputArray.get(index);
            String result = "    " + order + ":" + todo.toString();
            System.out.println(result);
        }
        printDivider();
    }

    public static void echoForAdd(String input) {
        printDivider();
        System.out.println("    added: " + input);
        printDivider();
    }
}
