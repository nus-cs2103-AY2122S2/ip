import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String botName = "Duke";
        Printer.printDivider();
        System.out.println("    Hello, I'm " + botName + ".");
        System.out.println("    What can I do for you?");
        Printer.printDivider();
        Scanner inputScanner = new Scanner(System.in);
        String input = "";
        ArrayList<TodoObject> todo = new ArrayList<>();

        while (!input.equals("bye")) {
            input = inputScanner.nextLine();
            String[] inputArray = input.split(" ");
            TodoObject todoObj = new TodoObject(input);
            if (input.equals("bye")) {
                Printer.printEndMessage();
            } else if(input.equals("list")) {
                Printer.printTodo(todo);
            }
            else if (inputArray.length > 1) {
                String firstArg = input.substring(0, input.indexOf(" "));
                if (firstArg.equals("mark")) {
                    todo.get(Integer.parseInt(inputArray[1]) - 1).mark();
                } else if (firstArg.equals("unmark")) {
                    todo.get(Integer.parseInt(inputArray[1]) - 1).unmark();
                } else {
                    Printer.echoForAdd(input);
                    todo.add(todoObj);
                }
            }
            else {
                Printer.echoForAdd(input);
                todo.add(todoObj);
            }
        }
    }
}
