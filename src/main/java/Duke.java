import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        final String HORIZONTAL_LINE = "\t" + "____________________________________________________________";
        Task[] savedTask = new Task[100];
        int counting = 0;

        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t" + "Hello! I'm Duke");
        System.out.println("\t" + "What can I do for you?");
        System.out.println(HORIZONTAL_LINE);

        Scanner sc = new Scanner(System.in);

        while (true) {
            String userInput = sc.nextLine();
            String[] commands = userInput.strip().split(" ");

            if (commands[0].equals("bye")) {
                System.out.println(HORIZONTAL_LINE);
                System.out.println("\t" + "Bye. Hope to see you again soon!");
                System.out.println(HORIZONTAL_LINE);
                break;
            } else if (commands[0].equals("list")) {
                System.out.println(HORIZONTAL_LINE);
                System.out.println("\t" + "Here are the tasks in your list:");
                for (int i = 0; i < counting; i++) {
                    int index = i + 1;
                    System.out.println("\t" + index + "." + savedTask[i].toString());
                }
                System.out.println(HORIZONTAL_LINE);
            } else if (commands[0].equals("mark")) {
                System.out.println(HORIZONTAL_LINE);
                System.out.println("\t" + "Nice! I've marked this task as done:");
                int index = Integer.parseInt(commands[1]) - 1;
                savedTask[index].markAsDone();
                System.out.println("\t" + savedTask[index].toString());
            } else if (commands[0].equals("unmark")) {
                System.out.println(HORIZONTAL_LINE);
                System.out.println("\t" + "OK, I've marked this task as not done yet:");
                int index = Integer.parseInt(commands[1]) - 1;
                savedTask[index].markAsNotDone();
                System.out.println("\t" + savedTask[index].toString());
            } else {
                savedTask[counting] = new Task(userInput);
                counting++;
                System.out.println(HORIZONTAL_LINE);
                System.out.println("\t" + "added: " + userInput);
                System.out.println(HORIZONTAL_LINE);
            }
        }
    }
}
