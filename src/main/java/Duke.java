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
    static String addedTask = lineDivider + "Got it. I've added this task:\n" +
            "   %s" + "\nNow you have %d tasks in the list.\n" + lineDivider;

    public static void listOut(Task[] list, int n) {
        System.out.printf(lineDivider + "Here are the tasks in your list:\n");
        for (int i = 0; i < n; i++) {
            System.out.printf("%d." + list[i].toString() + "\n", i+1);
        }
    }

    public static void markTask(Task[] list, String echo, int indexOfSpace) {
        int targetIndex = Integer.parseInt(echo.substring(indexOfSpace + 1));
        Task curr = list[targetIndex - 1];
        curr.mark();
        String status = curr.getStatus();
        String description = curr.getDescription();
        System.out.printf(lineDivider + "Nice! I've marked this task as done:\n" +
                "[%s] " + " %s\n" + lineDivider, status, description);
    }

    public static void unMarkTask(Task[] list, String echo, int indexOfSpace) {
        int targetIndex = Integer.parseInt(echo.substring(indexOfSpace + 1));
        Task curr = list[targetIndex - 1];
        curr.unMark();
        String status = curr.getStatus();
        String description = curr.getDescription();
        System.out.printf(lineDivider + "Ok, I've marked this task as not done yet:\n" +
                "[%s] " + "%s\n" + lineDivider, status, description);
    }

    public static void addTask(Task[] list, int n, String echo) {
        Task newTask = new Task(echo);
        list[n] = newTask;
        System.out.printf(addedTask, echo, n+1);
    }

    public static void addDeadline(Task[] list, int n, String info) {
        int indexOfSpace = info.indexOf("/");
        String description = info.substring(0, indexOfSpace - 1);
        String date = info.substring(indexOfSpace + 4);
        Deadline curr = new Deadline(description, date);
        list[n] = curr;
        System.out.printf(addedTask, curr.toString(), n+1);
    }

    public static void addEvent(Task[] list, int n, String info) {
        int indexOfSpace = info.indexOf("/");
        String description = info.substring(0, indexOfSpace - 1);
        String date = info.substring(indexOfSpace + 4);
        Event curr = new Event(description, date);
        list[n] = curr;
        System.out.printf(addedTask, curr.toString(), n+1);
    }

    public static void addTodo(Task[] list, int n,String info) {
        String description = info;
        Todo curr = new Todo(description);
        list[n] = curr;
        System.out.printf(addedTask, curr.toString(), n+1);
    }

    public static void main(String[] args) {
        Task[] listOfTasks = new Task[100];
        int n = 0;

        System.out.printf(greet);
        Scanner cmd = new Scanner(System.in);  // Create a Scanner object
        while (true) {
            String echo = cmd.nextLine();
            if (echo.equals("bye")) {
                break;
            }
            if (echo.equalsIgnoreCase("list")) {
                listOut(listOfTasks, n);
                System.out.printf(lineDivider);
                continue;
            } else if (echo.contains(" ")) {
                int indexOfSpace = echo.indexOf(" ");
                String wordOne = echo.substring(0, indexOfSpace);
                if (wordOne.equalsIgnoreCase("mark")) {
                    markTask(listOfTasks, echo, indexOfSpace);
                    continue;
                } else if (wordOne.equalsIgnoreCase("unmark")) {
                    unMarkTask(listOfTasks, echo, indexOfSpace);
                    continue;
                } else if (wordOne.equalsIgnoreCase("deadline")) {
                    addDeadline(listOfTasks, n, echo.substring(indexOfSpace + 1));
                    n = n+1;
                    continue;
                } else if (wordOne.equalsIgnoreCase("event")) {
                    addEvent(listOfTasks, n, echo.substring(indexOfSpace + 1));
                    n = n+1;
                    continue;
                } else if (wordOne.equalsIgnoreCase("todo")) {
                    addTodo(listOfTasks, n, echo.substring(indexOfSpace + 1));
                    n = n+1;
                    continue;
                }
            }
        }
        System.out.printf(lineDivider + goodBye + "\n" + lineDivider);
    }
}
