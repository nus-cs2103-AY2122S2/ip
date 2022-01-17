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
            String[] commands = userInput.strip().split(" ", 2);

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
                System.out.println(HORIZONTAL_LINE);
            } else if (commands[0].equals("unmark")) {
                System.out.println(HORIZONTAL_LINE);
                System.out.println("\t" + "OK, I've marked this task as not done yet:");
                int index = Integer.parseInt(commands[1]) - 1;
                savedTask[index].markAsNotDone();
                System.out.println("\t" + savedTask[index].toString());
                System.out.println(HORIZONTAL_LINE);
            } else {
                switch (commands[0]) {
                    case "todo":
                        savedTask[counting] = new Todo(commands[1]);
                        break;
                    case "deadline":
                        String[] deadlineDetail = commands[1].split("/by");
                        savedTask[counting] = new Deadline(deadlineDetail[0], deadlineDetail[1]);
                        break;
                    case "event":
                        String[] eventDetail = commands[1].split("/at");
                        savedTask[counting] = new Event(eventDetail[0], eventDetail[1]);
                        break;
                }
                System.out.println(HORIZONTAL_LINE);
                System.out.println("\t" + "Got it. I've added this task:");
                System.out.println("\t" + savedTask[counting].toString());
                System.out.println("\t" + "Now you have " + (counting + 1) + " tasks in the list.");
                System.out.println(HORIZONTAL_LINE);
                counting++;
            }
        }
    }
}
