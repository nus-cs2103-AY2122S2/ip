import exception.*;
import task.*;
import enums.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static int count = 0;
    private static boolean processNext = true;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public static void main(String[] args) {
        welcome();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (processNext) {
            try {
                String[] tokens = input.split(" ");
                switch (Command.valueOf(tokens[0].trim().toUpperCase())) {
                case BYE:
                    processNext = false;
                    echo("Goodbye. J.A.R.V.I.S. systems powering off...");
                    return;
                case LIST:
                    printTasks();
                    break;
                case MARK:
                    markAsDone(tokens);
                    break;
                case UNMARK:
                    markAsUndone(tokens);
                    break;
                case DELETE:
                    delete(tokens);
                    break;
                case TODO:
                    addTodo(input);
                    break;
                case DEADLINE:
                    addDeadline(input);
                    break;
                case EVENT:
                    addEvent(input);
                    break;
                default:
                    break;
                }
            } catch (IllegalArgumentException e) {
                echo("I'm afraid I don't understand your request.");
            } catch (DukeException de) {
                echo("I'm afraid I wasn't able to fulfill your request.\n" + de.getMessage());
            }
            input = scanner.nextLine();
        }
        scanner.close();
    }

    private static void welcome() {
        String logo =
                "      _          _          ____       __     __      ___       ____\n"
                        + "     | |        / \\        |  _ \\      \\ \\   / /     |_ _|     / ___|\n"
                        + "  _  | |       / _ \\       | |_) |      \\ \\ / /       | |      \\___ \\\n"
                        + " | |_| |  _   / ___ \\   _  |  _ <   _    \\ V /    _   | |   _   ___) |  _\n"
                        + "  \\___/  (_) /_/   \\_\\ (_) |_| \\_\\ (_)    \\_/    (_) |___| (_) |____/  (_)\n\n";

        System.out.println("Starting up...\n"
                + "Online and ready.\n"
                + logo
                + "At your service.\n");
    }

    private static void echo(String str) {
        System.out.println("------------------------------------------------------------");
        System.out.println(str);
        System.out.println("------------------------------------------------------------");
    }

    private static void printTasks() {
        if (count == 0) {
            echo("You have no tasks in your list. :-)");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(i + 1).append(".\t").append(tasks.get(i));
            if (i + 1 != count) {
                sb.append("\n");
            }
        }
        echo(sb.toString());
    }

    private static void markAsDone(String[] tokens) throws DukeException {
        if (count == 0) {
            throw new DukeException("You have no tasks in your list.");
        }

        int num;

        try {
            num = Integer.parseInt(tokens[1]) - 1;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please specify a task number to mark as completed.");
        } catch (NumberFormatException e) {
            throw new DukeException("Please specify the task number numerically.");
        }
        try {
            Task task = tasks.get(num);
            task.markAsDone();
            echo("I've marked the following task as completed:\n\t" + task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please specify a valid task number (between 1 to " + count + " inclusive).");
        }
    }

    private static void markAsUndone(String[] tokens) throws DukeException {
        if (count == 0) {
            throw new DukeException("You have no tasks in your list.");
        }

        int num;

        try {
            num = Integer.parseInt(tokens[1]) - 1;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please specify a task number to mark as incomplete.");
        } catch (NumberFormatException e) {
            throw new DukeException("Please specify the task number numerically.");
        }
        try {
            Task task = tasks.get(num);
            task.markAsUndone();
            echo("I've marked the following task as incomplete:\n\t" + task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please specify a valid task number (between 1 to " + count + " inclusive).");
        }
    }

    private static void delete(String[] tokens) throws DukeException {
        if (count == 0) {
            throw new DukeException("You have no tasks in your list.");
        }

        int num;

        try {
            num = Integer.parseInt(tokens[1]) - 1;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please specify a task number to delete.");
        } catch (NumberFormatException e) {
            throw new DukeException("Please specify the task number numerically.");
        }
        try {
            Task task = tasks.remove(num);
            count--;
            echo("Understood. I've removed the following task:\n\t"
                    + task + "\n"
                    + "Now you have " + count + " task(s) in your list.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please specify a valid task number (between 1 to " + count + " inclusive).");
        }
    }

    private static void addTodo(String input) throws DukeException {
        String description;
        try {
            description = input.trim().substring(Command.TODO.toString().length() + 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The description of the todo cannot be empty.");
        }

        Todo todo = new Todo(description);
        tasks.add(todo);
        count++;
        echo("Got it. I've added this todo:\n\t"
                + todo + "\n"
                + "Now you have " + count + " task(s) in your list.");
    }

    private static void addDeadline(String input) throws DukeException {
        String description;
        LocalDateTime dateTime;
        String[] split = input.split("/by");

        try {
            description = split[0].trim().substring(Command.DEADLINE.toString().length() + 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The description of the deadline cannot be empty.");
        }
        if (split.length == 1) {
            throw new DukeException("Please specify the date of the deadline (usage: `deadline <description> /by <date>`).");
        }
        if (split[1].trim().equals("")) {
            throw new DukeException("The date of the deadline cannot be empty.");
        }
        try {
            dateTime = LocalDateTime.parse(split[1].trim(), formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please specify the date format as follows: 2022-12-25 2359");
        }

        Deadline deadline = new Deadline(description, dateTime);
        tasks.add(deadline);
        count++;
        echo("Got it. I've added this deadline:\n\t"
                + deadline + "\n"
                + "Now you have " + count + " task(s) in your list.");
    }

    public static void addEvent(String input) throws DukeException {
        String description;
        LocalDateTime dateTime;
        String[] split = input.split("/at");

        try {
            description = split[0].trim().substring(Command.EVENT.toString().length() + 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The description of the event cannot be empty.");
        }
        if (split.length == 1) {
            throw new DukeException("Please specify the date of the event (usage: `event <description> /at <date>`).");
        }
        if (split[1].trim().equals("")) {
            throw new DukeException("The date of the event cannot be empty.");
        }
        try {
            dateTime = LocalDateTime.parse(split[1].trim(), formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please specify the date format as follows: 2022-12-25 2359");
        }


        Event event = new Event(description, dateTime);
        tasks.add(event);
        count++;
        echo("Got it. I've added this event:\n\t"
                + event + "\n"
                + "Now you have " + count + " task(s) in your list.");
    }
}
