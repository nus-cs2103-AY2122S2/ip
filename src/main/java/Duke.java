import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;  // Import the Scanner class
public class Duke {
    public static void main(String[] args) {
        String lineDivider = "____________________________________________________________\n";
        String greet = lineDivider
                + "Hello! I'm Mum!\n"
                + "What can I do for you?\n"
                + lineDivider;
        String goodBye = "Bye. Hope to see you again soon!";
        String added = lineDivider + "added: " + "%s" + "\n" + lineDivider;
        List<String> list = new ArrayList<>(100);

        System.out.printf(greet);

        while (true) {
            Scanner cmd = new Scanner(System.in);  // Create a Scanner object
            String echo = cmd.nextLine();
            if (echo.equals("bye")) {
                break;
            }
            if (echo.equalsIgnoreCase("list")) {
                int sizeOfList = list.size();
                System.out.printf(lineDivider);
                for (int i = 0; i < sizeOfList; i++) {
                    System.out.printf("%d. " + "%s" + "\n", i+1, list.get(i));
                }
                System.out.printf(lineDivider);
            } else {
                list.add(echo);
                System.out.printf(added, echo);
            }
        }
        System.out.printf(lineDivider + goodBye + "\n" + lineDivider);
    }
}
