import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");

        Scanner sc = new Scanner(System.in);

        System.out.println("What can I do for you?");
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.printf("%d.[%s] %s%n", i + 1, tasks.get(i).getStatusIcon(), tasks.get(i).description);
                }
            } else if (isMarkCommand(command)) {
                String[] commandArr = command.split(" ", 2);

                Task task = tasks.get(Integer.parseInt(commandArr[1]) - 1);
                task.markAsDone();

                System.out.println("Nice! I've marked this task as done:");
                System.out.printf("[%s] %s%n", task.getStatusIcon(), task.description);
            } else if (isUnmarkCommand(command)) {
                String[] commandArr = command.split(" ", 2);

                Task task = tasks.get(Integer.parseInt(commandArr[1]) - 1);
                task.markAsNotDone();

                System.out.println("OK, I've marked this task as not done yet:");
                System.out.printf("[%s] %s%n", task.getStatusIcon(), task.description);
            } else {
                tasks.add(new Task(command));
                System.out.println("added: " + command);
            }
            command = sc.nextLine();
        }

        sc.close();
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static boolean isMarkCommand(String command) {
        String[] commandArr = command.split(" ", 2);
        if (commandArr.length < 2) {
            return false;
        }
        String description = commandArr[0];
        String digit = commandArr[1];
        if (digit.matches("\\d+")) {
            int index = Integer.parseInt(digit);
            return description.equals("mark") && index > 0 && index <= tasks.size();
        }
        return false;
    }

    public static boolean isUnmarkCommand(String command) {
        String[] commandArr = command.split(" ", 2);
        if (commandArr.length < 2) {
            return false;
        }
        String description = commandArr[0];
        String digit = commandArr[1];
        if (digit.matches("\\d+")) {
            int index = Integer.parseInt(digit);
            return description.equals("unmark") && index > 0 && index <= tasks.size();
        }
        return false;
    }
}
