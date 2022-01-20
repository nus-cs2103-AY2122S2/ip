import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        Scanner sc = new Scanner(System.in);
        Task[] taskList = new Task[100];
        int currIndex = 0;
        System.out.println("____________________________________________________________\nHello! I'm Duke\nWhat can I do for you?\n____________________________________________________________");
        String input = sc.nextLine();
        String[] splitted = input.split(" ");
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println("____________________________________________________________\n");
                System.out.print("Here are the tasks in your list:");
                for (int i=0; i < currIndex; i++ ) {
                    String checkbox = taskList[i].done? "[X]" : "[ ]";
                    System.out.println(String.valueOf(i+1) + ". " + checkbox + " " + taskList[i].task);
                }
                System.out.println("____________________________________________________________\n");
            } else if (splitted[0].equals("mark")) {
                int index = Integer.valueOf(splitted[1]);
                taskList[index-1].done = true;
                System.out.println("____________________________________________________________\n" +
                        "Nice! I've marked this task as done: \n" +
                        "[X] " + taskList[index-1].task
                + "\n____________________________________________________________");
            } else if (splitted[0].equals("unmark")) {
                int index = Integer.valueOf(splitted[1]);
                taskList[index-1].done = false;
                System.out.println("____________________________________________________________\n" +
                        "OK, I've marked this task as not done yet: \n" +
                        "[ ] " + taskList[index-1].task
                        + "\n____________________________________________________________");
            } else {
                System.out.println("____________________________________________________________\n" +
                        "added: " + input + "\n" +
                        "____________________________________________________________");
                taskList[currIndex] = new Task(input, false);
                currIndex += 1;
            }
            input = sc.nextLine();
            splitted = input.split(" ");
        }
        System.out.println("____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________");
    }
}
