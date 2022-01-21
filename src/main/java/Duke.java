import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();
        System.out.println("____________________________________________________________\nHello! I'm Duke\nWhat can I do for you?\n____________________________________________________________");
        String input = sc.nextLine();
        String[] splitted = input.split(" ", 2);
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println(i + 1 + ". " + taskList.get(i));
                }
                System.out.println("____________________________________________________________");
            } else if (splitted[0].equals("delete")) {
                int index = Integer.parseInt(splitted[1]);
                System.out.println("____________________________________________________________\n" +
                        "Noted. I've removed this task:\n" +
                         taskList.get(index - 1) +
                        "\nNow you have " + taskList.size() + " tasks in the list."
                        + "\n____________________________________________________________");
                taskList.remove(index -1);
            } else if (splitted[0].equals("todo")) {
                if (input.equals("todo") || input.equals("todo ")) {
                    System.out.println("____________________________________________________________\n" +
                            " ☹ OOPS!!! Please describe your todo :-(\n" +
                            "____________________________________________________________");
                } else {
                    taskList.add(new Todo(splitted[1], false));
                    System.out.println("____________________________________________________________\n" +
                            "Got it. I've added this task: \n" +
                            taskList.get(taskList.size() -1) +
                            "\nNow you have " + taskList.size() + " tasks in the list."
                            + "\n____________________________________________________________");
                }
            } else if (splitted[0].equals("deadline")) {
                String[] time = splitted[1].split("/by", 2);
                taskList.add(new Deadline(time[0], false, time[1]));
                System.out.println("____________________________________________________________\n" +
                        "Got it. I've added this task: \n" +
                        taskList.get(taskList.size() -1) +
                        "\nNow you have " + taskList.size() + " tasks in the list."
                        + "\n____________________________________________________________");
            } else if (splitted[0].equals("event")) {
                String[] time = splitted[1].split("/at");
                taskList.add(new Event(time[0], false, time[1]));
                System.out.println("____________________________________________________________\n" +
                        "Got it. I've added this task: \n" +
                        taskList.get(taskList.size() - 1) +
                        "\nNow you have " + taskList.size() + " tasks in the list."
                        + "\n____________________________________________________________");
            } else if (splitted[0].equals("mark")) {
                int index = Integer.parseInt(splitted[1]);
                taskList.get(index - 1).done = true;
                System.out.println("____________________________________________________________\n" +
                        "Nice! I've marked this task as done: \n" +
                        taskList.get(index - 1)
                + "\n____________________________________________________________");
            } else if (splitted[0].equals("unmark")) {
                int index = Integer.parseInt(splitted[1]);
                taskList.get(index - 1).done = false;
                System.out.println("____________________________________________________________\n" +
                        "OK, I've marked this task as not done yet: \n" +
                        taskList.get(index - 1)
                        + "\n____________________________________________________________");
            } else {
                // throw new DukeException("I'm sorry, but I don't know what that means :-(");
                System.out.println("____________________________________________________________\n" +
                        " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                        "____________________________________________________________");
            }
            input = sc.nextLine();
            splitted = input.split(" ", 2);
        }
        System.out.println("____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________");
    }
}
