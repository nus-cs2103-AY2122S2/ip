import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static TodoList todoList = new TodoList();
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Hello from\n" + Response.LOGO);
        Format.wrapPrint(Response.WELCOME);
        boolean over = false;
        while (!over) {
            String command = myScanner.nextLine();
            String[] words  = command.split(" ");
            String firstWord = words[0];
            if (firstWord.equals("bye")) {
                Format.wrapPrint(Response.GOODBYE);
                over = true;
                continue;
            } else if (firstWord.equals("list")) {
                Format.wrapPrint(todoList.toString());
            } else if (firstWord.equals("mark") && words.length == 2)  {
                try {
                    String change = todoList.markFinished(Integer.parseInt(words[1]));
                    Format.wrapPrint(change);
                } catch (NumberFormatException e) {
                    Format.wrapPrint("mark command must precede with a decimal number!");
                }
            } else if (firstWord.equals("unmark") && words.length == 2)  {
                try {
                    String change = todoList.unmarkFinished(Integer.parseInt(words[1]));
                    Format.wrapPrint(change);
                } catch (NumberFormatException e) {
                    Format.wrapPrint("unmark command must precede with a decimal number!");
                }
            } else {
                todoList.addItem(new Item(command));
                Format.wrapPrint(command);
            }
        }
    }
}
