import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;  // Import the Scanner class
public class Duke {
    static String lineDivider = "____________________________________________________________\n";
    static String greet = lineDivider
            + "Hello! I'm Mum!\n"
            + "What can I do for you?\n"
            + lineDivider;
    static String goodBye = "Bye. Hope to see you again soon!";
    static String added = lineDivider + "added: " + "%s" + "\n" + lineDivider;

    public static void listOut(List list) {
        int sizeOfList = list.size();
        System.out.printf(lineDivider + "Here are the tasks in your list:\n");
        for (int i = 0; i < sizeOfList; i++) {
            Task curr = (Task) list.get(i);
            System.out.printf("%d.[%s] " + "%s\n", i+1, curr.getStatus(), curr.getDescription());
        }
    }

    public static void markTask(List list, String echo, int indexOfSpace) {
        int targetIndex = Integer.parseInt(echo.substring(indexOfSpace + 1));
        Task curr = (Task) list.get(targetIndex - 1);
        curr.mark();
        String status = curr.getStatus();
        String description = curr.getDescription();
        System.out.printf(lineDivider + "Nice! I've marked this task as done:\n" +
                "[%s] " + "%s\n" + lineDivider, status, description);
    }

    public static void unMarkTask(List list, String echo, int indexOfSpace) {
        int targetIndex = Integer.parseInt(echo.substring(indexOfSpace + 1));
        Task curr = (Task) list.get(targetIndex - 1);
        curr.unMark();
        String status = curr.getStatus();
        String description = curr.getDescription();
        System.out.printf(lineDivider + "Ok, I've marked this task as not done yet:\n" +
                "[%s] " + "%s\n" + lineDivider, status, description);
    }

    public static void addTask(List list, String echo) {
        Task newTask = new Task(echo);
        list.add(newTask);
        System.out.printf(added, echo);
    }

    public static void main(String[] args) {
        List<Task> list = new ArrayList<>(100);

        System.out.printf(greet);
        while (true) {
            Scanner cmd = new Scanner(System.in);  // Create a Scanner object
            String echo = cmd.nextLine();
            if (echo.equals("bye")) {
                break;
            }
            if (echo.equalsIgnoreCase("list")) {
                listOut(list);
                System.out.printf(lineDivider);
                continue;
            } else if (echo.contains(" ")) {
                int indexOfSpace = echo.indexOf(" ");
                String wordOne = echo.substring(0, indexOfSpace);
                if (wordOne.equalsIgnoreCase("mark")) {
                    markTask(list, echo, indexOfSpace);
                    continue;
                } else if (wordOne.equalsIgnoreCase("unmark")) {
                    unMarkTask(list, echo, indexOfSpace);
                    continue;
                }
            }
            addTask(list, echo);
        }
        System.out.printf(lineDivider + goodBye + "\n" + lineDivider);
    }
}
