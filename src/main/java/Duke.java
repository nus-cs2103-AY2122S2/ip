import java.util.ArrayList;
import java.util.Scanner;  // Import the Scanner class
public class Duke {
    static String lineDivider = "____________________________________________________________\n";
    static String greet = lineDivider
            + "Hello! I'm Mum!\n"
            + "What can I do for you?\n"
            + lineDivider;
    static String goodBye = "Bye. Hope to see you again soon!";
    static String addedTask = lineDivider + "Got it. I've added this task:\n" +
            "   %s\nNow you have %d tasks in the list.\n" + lineDivider;

    public static void listOut(ArrayList<Task> list, int n) {
        System.out.printf(lineDivider + "Here are the tasks in your list:\n");
        for (int i = 0; i < n; i++) {
            System.out.printf("%d." + list.get(i).toString() + "\n", i+1);
        }
    }

    public static void deleteTask(ArrayList<Task> list, String[] echo, int size) throws DukeException {
        String err = "Oh no! Which task do you wish to delete? Try again :)\n" + lineDivider;
        String wrongNumber = "Oh no! This task number does not exist. Try again :)\n" + lineDivider;
        String wrongFormat = "Oh no! Please do not spell out the number. Try again :)\n" + lineDivider;
        int targetIndex;
        if (echo.length == 1) {
            throw new DukeException(err);
        }
        String taskNum = echo[1];
        if (taskNum.isEmpty()) {
            throw new DukeException(err);
        }
        try {
            targetIndex = Integer.parseInt(taskNum);
        } catch (Exception e) {
            throw new DukeException(wrongFormat);
        }
        if (targetIndex > size || targetIndex <= 0) {
            throw new DukeException(wrongNumber);
        } else {
            Task curr = list.get(targetIndex - 1);
            list.remove(targetIndex - 1);
            System.out.printf(lineDivider + "Noted. I've removed this task:\n   " +
                    curr.toString() + "\nNow you have %d tasks in the list.\n" + lineDivider, size-1);
        }
    }


    public static void markTask(ArrayList<Task> list, String[] echo, int size) throws DukeException{
        String err = "Oh no! Which task do you wish to mark? Try again :)\n" + lineDivider;
        String wrongNumber = "Oh no! This task number does not exist. Try again :)\n" + lineDivider;
        String wrongFormat = "Oh no! Please do not spell out the number. Try again :)\n" + lineDivider;
        int targetIndex;
        if (echo.length == 1) {
            throw new DukeException(err);
        }
        String taskNum = echo[1];
        if (taskNum.isEmpty()) {
            throw new DukeException(err);
        }
        try {
            targetIndex = Integer.parseInt(taskNum);
        } catch (Exception e) {
            throw new DukeException(wrongFormat);
        }
        if (targetIndex > size || targetIndex <= 0) {
            throw new DukeException(wrongNumber);
        } else {
            Task curr = list.get(targetIndex - 1);
            curr.mark();
            String status = curr.getStatus();
            String description = curr.getDescription();
            System.out.printf(lineDivider + "Nice! I've marked this task as done:\n" +
                    "[%s] " + " %s\n" + lineDivider, status, description);
        }
    }

    public static void unMarkTask(ArrayList<Task> list, String[] echo, int size) throws DukeException {
        String err = "Oh no! Which task do you wish to unmark? Try again :)\n" + lineDivider;
        String wrongNumber = "Oh no! This task number does not exist. Try again :)\n" + lineDivider;
        String wrongFormat = "Oh no! Please do not spell out the number. Try again :)\n" + lineDivider;
        int targetIndex;
        if (echo.length == 1) {
            throw new DukeException(err);
        }
        String taskNum = echo[1];
        if (taskNum.isEmpty()) {
            throw new DukeException(err);
        }
        try {
            targetIndex = Integer.parseInt(taskNum);
        } catch (Exception e) {
            throw new DukeException(wrongFormat);
        }
        if (targetIndex > size || targetIndex <= 0) {
            throw new DukeException(wrongNumber);
        } else {
            Task curr = list.get(targetIndex - 1);
            curr.unMark();
            String status = curr.getStatus();
            String description = curr.getDescription();
            System.out.printf(lineDivider + "Ok, I've marked this task as not done yet:\n" +
                "[%s] " + "%s\n" + lineDivider, status, description);
        }
    }

    public static void addDeadline(ArrayList<Task> list, int n, String[] echo) throws DukeException {
        String err = "Oh no! The description of deadline cannot be empty... Try again :)\n" + lineDivider;
        String wrongFormat = "Oh no! The format for deadline task is wrong... Try again :)\n" + lineDivider;
        if (echo.length == 1) {
            throw new DukeException(err);
        }
        String description = echo[1];
        if (description.isEmpty()) {
            throw new DukeException(err);
        }
        String[] details = description.split(" /by ", 2);
        if (details.length == 1) {
            throw new DukeException(wrongFormat);
        }
        String info = details[0];
        String date = details[1];
        Deadline curr = new Deadline(info, date);
        list.add(curr);
        System.out.printf(addedTask, curr.toString(), n + 1);
    }

    public static void addEvent(ArrayList<Task> list, int n, String[] echo) throws DukeException {
        String err = "Oh no! The description of event cannot be empty... Try again :)\n" + lineDivider;
        String wrongFormat = "Oh no! The format for event task is wrong... Try again :)\n" + lineDivider;
        if (echo.length == 1) {
            throw new DukeException(err);
        }
        String description = echo[1];
        if (description.isEmpty()) {
            throw new DukeException(err);
        }
        String[] details = description.split(" /at ", 2);
        if (details.length == 1) {
            throw new DukeException(wrongFormat);
        }
        String info = details[0];
        String date = details[1];
        Event curr = new Event(info, date);
        list.add(curr);
        System.out.printf(addedTask, curr.toString(), n + 1);
    }

    public static void addTodo(ArrayList<Task> list, int n, String[] echo) throws DukeException {
        String err = "Oh no! The description of todo cannot be empty... Try again :)\n" + lineDivider;
        if (echo.length == 1) {
            throw new DukeException(err);
        }
        String description = echo[1];
        if (description.isEmpty()) {
            throw new DukeException(err);
        }
        Todo curr = new Todo(description);
        list.add(curr);
        System.out.printf(addedTask, curr.toString(), n + 1);
    }

    public static void main(String[] args) {
        ArrayList<Task> listOfTasks = new ArrayList<>(100);
        int n = 0;

        System.out.printf(greet);
        Scanner cmd = new Scanner(System.in);  // Create a Scanner object
        while (true) {
            try {
                String echo = cmd.nextLine();
                if (echo.equals("bye")) {
                    break;
                }
                if (echo.equalsIgnoreCase("list")) {
                    listOut(listOfTasks, n);
                    System.out.printf(lineDivider);
                } else if (echo.toLowerCase().contains("todo")) {
                    addTodo(listOfTasks, n, echo.split(" ", 2));
                    n = n+1;
                } else if (echo.toLowerCase().contains("event")) {
                    addEvent(listOfTasks, n, echo.split(" ", 2));
                    n = n+1;
                } else if (echo.toLowerCase().contains("deadline")) {
                    addDeadline(listOfTasks, n, echo.split(" ", 2));
                    n = n+1;
                } else if (echo.toLowerCase().contains("unmark")) {
                    unMarkTask(listOfTasks, echo.split(" ", 2), n);
                } else if (echo.toLowerCase().contains("mark")) {
                    markTask(listOfTasks, echo.split(" ", 2), n);
                } else if (echo.toLowerCase().contains("delete")) {
                    deleteTask(listOfTasks, echo.split(" ", 2), n);
                    n = n - 1;
                } else {
                    throw new DukeException("Oh no! I fear I don't understand! Try again!\n" + lineDivider);
                }
            } catch (DukeException e) {
                System.err.print(e);
            }
        }
        System.out.printf(lineDivider + goodBye + "\n" + lineDivider);
    }
}
