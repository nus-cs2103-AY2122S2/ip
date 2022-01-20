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
        int currIndex = 0;
        System.out.println("____________________________________________________________\nHello! I'm Duke\nWhat can I do for you?\n____________________________________________________________");
        String input = sc.nextLine();
        String[] splitted = input.split(" ", 2);
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println("____________________________________________________________\n");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < currIndex; i++) {
                    String checkbox = taskList.get(i).done ? "[X]" : "[ ]";
                    String statusbox = taskList.get(i).status.equals("") ? "" : "[" + taskList.get(i).status + "]";
                    System.out.println(String.valueOf(i + 1) + ". " + statusbox + checkbox + " " + taskList.get(i).task);
                }
                System.out.println("____________________________________________________________\n");
            } else if (splitted[0].equals("delete")) {
                currIndex -= 1;
                int index = Integer.parseInt(splitted[1]);
                String checkbox = taskList.get(index).done ? "[X]" : "[ ]";
                String statusbox = taskList.get(index).status.equals("") ? "" : "[" + taskList.get(index).status + "]";
                System.out.println("____________________________________________________________\n" +
                        "Noted. I've removed this task:\n" +
                         statusbox + checkbox + " " + taskList.get(index - 1).task +
                        "\nNow you have " + String.valueOf(currIndex) + " tasks in the list."
                        + "\n____________________________________________________________");
                taskList.remove(index -1);
            } else if (splitted[0].equals("todo")) {
                if (input.equals("todo") || input.equals("todo ")) {
                    System.out.println("____________________________________________________________\n" +
                            " ☹ OOPS!!! Please describe your todo :-(\n" +
                            "____________________________________________________________");
                } else {
                    taskList.add(new Task(splitted[1], false, "T"));
                    currIndex += 1;
                    System.out.println("____________________________________________________________\n" +
                            "Got it. I've added this task: \n" +
                            "[T][] " + taskList.get(currIndex - 1).task +
                            "\nNow you have " + String.valueOf(currIndex) + " tasks in the list."
                            + "\n____________________________________________________________");
                }
            } else if (splitted[0].equals("deadline")) {
                String[] time = splitted[1].split("/by");
                System.out.println(time[0]);
                taskList.add(new Task(time[0] + "(by" + time[1] + ")", false, "D"));
                currIndex += 1;
                System.out.println("____________________________________________________________\n" +
                        "Got it. I've added this task: \n" +
                        "[D][] " + taskList.get(currIndex - 1).task +
                        "\nNow you have " + String.valueOf(currIndex) + " tasks in the list."
                        + "\n____________________________________________________________");
            } else if (splitted[0].equals("event")) {
                String[] time = splitted[1].split("/at");
                taskList.add(new Task(time[0] + "(at" + time[1] + ")", false, "E"));
                currIndex += 1;
                System.out.println("____________________________________________________________\n" +
                        "Got it. I've added this task: \n" +
                        "[E][] " + taskList.get(currIndex - 1).task +
                        "\nNow you have " + String.valueOf(currIndex) + " tasks in the list."
                        + "\n____________________________________________________________");
            } else if (splitted[0].equals("mark")) {
                int index = Integer.parseInt(splitted[1]);
                taskList.get(index - 1).done = true;
                System.out.println("____________________________________________________________\n" +
                        "Nice! I've marked this task as done: \n" +
                        "[X] " + taskList.get(index - 1).task
                + "\n____________________________________________________________");
            } else if (splitted[0].equals("unmark")) {
                int index = Integer.parseInt(splitted[1]);
                taskList.get(index - 1).done = false;
                System.out.println("____________________________________________________________\n" +
                        "OK, I've marked this task as not done yet: \n" +
                        "[ ] " + taskList.get(index - 1).task
                        + "\n____________________________________________________________");
            } else {
                System.out.println("____________________________________________________________\n" +
                        " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                        "____________________________________________________________");
            }
            input = sc.nextLine();
            splitted = input.split(" ");
        }
        System.out.println("____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________");
    }
}
