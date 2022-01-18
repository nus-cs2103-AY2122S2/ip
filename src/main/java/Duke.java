import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Boot message
        String intro = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(wrap(intro));

        // Setup scanner for user input
        Scanner sc = new Scanner(System.in);

        // Store items in agenda
        List<Task> tasks = new ArrayList<>();

        while (sc.hasNextLine()) {
            try {
                String input = sc.nextLine();
                String[] breakdown = input.split(" ", 2);
                String command = breakdown[0];
                if (command.equals("bye")) {
                    break;
                } else if (command.equals("list")) {
                    StringBuilder result = new StringBuilder();
                    for (int i = 0; i < tasks.size(); i++) {
                        result.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
                    }
                    System.out.println(wrap("Here are the tasks in your list:\n" + result));
                } else {
                    if (command.equals("mark") || command.equals("unmark")) {
                        int index;
                        try {
                            index = Integer.parseInt(breakdown[1]) - 1;
                            if (index < 0 || index >= tasks.size()) {
                                throw new DukeException("Invalid index value");
                            }
                        } catch (NumberFormatException e) {
                            throw new DukeException("Invalid index format");
                        }
                        Task task = tasks.get(index);
                        if (command.equals("mark")) {
                            task.markAsDone();
                            System.out.println(wrap("Nice! I've marked this task as done:\n" + task + "\n"));
                        } else {
                            task.markAsUndone();
                            System.out.println(wrap("OK, I've marked this task as not done yet:\n" + task + "\n"));
                        }
                    } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                        String options;
                        try {
                            options = breakdown[1];
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeException("Too few arguments supplied");
                        }
                        Task task;
                        if (command.equals("todo")) {
                            task = new Todo(options);
                        } else if (command.equals("deadline")) {
                            String[] splitCommand = options.split("/by");
                            if (splitCommand.length < 2) {
                                throw new DukeException("No deadline time supplied");
                            }
                            String description = splitCommand[0];
                            String byTime = splitCommand[1];
                            task = new Deadline(description, byTime);
                        } else {
                            String[] splitCommand = options.split("/at");
                            if (splitCommand.length < 2) {
                                throw new DukeException("No event time supplied");
                            }
                            String description = splitCommand[0];
                            String atTime = splitCommand[1];
                            task = new Event(description, atTime);
                        }
                        tasks.add(task);

                        System.out.println(wrap("Got it. I've added this task:\n"
                                + task + "\n"
                                + "Now you have " + tasks.size() + " task(s) in the list.\n"));
                    } else {
                        throw new DukeException("Invalid command");
                    }
                }
            } catch (DukeException e) {
                e.printStackTrace();
            }
        }

        System.out.println(wrap("Bye. Hope to see you again soon!\n"));
        sc.close();
    }

    protected static String wrap(String text) {
        return "__________________________________________________\n"
                + text
                + "__________________________________________________\n";
    }
}
