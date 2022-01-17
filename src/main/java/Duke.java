import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Boot message
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String intro = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(wrap(logo + "\n" + intro));

        // Setup scanner for user input
        Scanner sc = new Scanner(System.in);

        // Store items in agenda
        List<Task> tasks = new ArrayList<>();

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] breakdown = input.split(" ");
            String command = breakdown[0];
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                StringBuilder result = new StringBuilder();
                for (int i = 0; i < tasks.size(); i++) {
                    result.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
                }
                System.out.println(wrap(result.toString()));
            } else if (command.equals("mark")) {
                Task task = tasks.get(Integer.parseInt(breakdown[1]) - 1);
                task.markAsDone();
                System.out.println(wrap("Nice! I've marked this task as done:\n" + task + "\n"));
            } else if (command.equals("unmark")) {
                Task task = tasks.get(Integer.parseInt(breakdown[1]) - 1);
                task.markAsUndone();
                System.out.println(wrap("OK, I've marked this task as not done yet:\n" + task + "\n"));
            } else {
                tasks.add(new Task(input));
                System.out.println(wrap("added: " + input + "\n"));
            }
        }

        System.out.println(wrap("Bye. Hope to see you again soon!\n"));
        sc.close();
    }

    public static String wrap(String text) {
        return "__________________________________________________\n"
                + text
                + "__________________________________________________\n";
    }
}
