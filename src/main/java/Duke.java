import java.util.*;
import java.util.regex.*;

public class Duke {
    private static List list;
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I'm\n" + logo + "I am at your service.\n");
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        list = new List(100);

        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                System.out.println(list);
            }
            else if (Pattern.matches("mark \\d+", userInput)) {
                String str = "";
                for (int i = 5; i < userInput.length(); i++) {
                    str = str + userInput.charAt(i);
                }
                int number = Integer.parseInt(str) - 1;
                System.out.println(list.markDone(number));
            }
            else if (Pattern.matches("unmark \\d+", userInput)) {
                String str = "";
                for (int i = 7; i < userInput.length(); i++) {
                    str = str + userInput.charAt(i);
                }
                int number = Integer.parseInt(str) - 1;
                System.out.println(list.unmarkDone(number));
            }
            else {
                Task task = new Task(userInput);
                System.out.println(list.add(task));
            }
            userInput = sc.nextLine();
        }
        System.out.println("Ciao! Hope to see you again!");
    }
}
