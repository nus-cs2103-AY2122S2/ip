import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String botName = "Duke";
        printDivider();
        System.out.println("    Hello, I'm " + botName + ".");
        System.out.println("    What can I do for you?");
        printDivider();
        Scanner inputScanner = new Scanner(System.in);
        String input = "";
        ArrayList<String> todo = new ArrayList<>();

        while (!input.equals("bye")) {
            input = inputScanner.nextLine();
            if (input.equals("bye")) {
                printEndMessage();
            } else if(input.equals("list")) {
                printTodo(todo);
            } else {
                echoForAdd(input);
                todo.add(input);
            }
        }
    }

    public static void printEndMessage() {
        printDivider();
        System.out.println("    Bye. Hope to see you again soon!");
        printDivider();
    }

    public static void printDivider() {
        String divider = "    ____________________________________________________________";
        System.out.println(divider);
    }

    public static void printTodo(ArrayList<String> inputArray) {
        printDivider();
        for (int index = 0; index < inputArray.size(); index++) {
            int order = (index + 1);
            String result = "    " + order + ": " + inputArray.get(index);
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
