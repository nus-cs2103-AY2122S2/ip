import exception.*;
import task.*;
import enums.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String dataFilePath = "data/data.txt";

    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static boolean processNext = true;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public static void main(String[] args) throws DukeException {
        welcome();
        loadData();

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

    private static void loadData() throws DukeException {
        try {
            File dataFile = new File(dataFilePath);
            if (dataFile.exists()) {
                Scanner s = new Scanner(dataFile);
                while (s.hasNext()) {
                    String[] line = s.nextLine().split("\\|");
                    Task task;
                    switch (line[0]) {
                    case "T":
                        task = new Todo(line[2]);
                        break;
                    case "D":
                        task = new Deadline(line[2], LocalDateTime.parse(line[3]));
                        break;
                    case "E":
                        task = new Event(line[2], LocalDateTime.parse(line[3]));
                        break;
                    default:
                        throw new DukeException("Unexpected task type encountered: " + line[0]);
                    }

                    if (line[1].equals("1")) {
                        task.markAsDone();
                    }

                    tasks.add(task);
                }
            } else {
                dataFile.getParentFile().mkdirs();
                dataFile.createNewFile();
            }
        } catch (IOException e) {
            throw new DukeException("An error has occurred whilst retrieving your tasks.");
        }
    }

    private static void saveChanges() throws DukeException {
        try {
            StringBuilder sb = new StringBuilder();
            for (Task t : tasks) {
                sb.append(t.toFileString()).append("\n");
            }
            FileWriter fw = new FileWriter(dataFilePath);
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new DukeException("An error has occurred whilst saving your tasks.");
        }
    }

    private static void printTasks() {
        int count = tasks.size();
        if (count == 0) {
            echo("You have no tasks in your list. :-)");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(".\t").append(tasks.get(i));
            if (i + 1 != count) {
                sb.append("\n");
            }
        }
        echo(sb.toString());
    }

    private static void markAsDone(String[] tokens) throws DukeException {
        if (tasks.size() == 0) {
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
            throw new DukeException("Please specify a valid task number (between 1 to " + tasks.size() + " inclusive).");
        }
        saveChanges();
    }

    private static void markAsUndone(String[] tokens) throws DukeException {
        if (tasks.size() == 0) {
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
            throw new DukeException("Please specify a valid task number (between 1 to " + tasks.size() + " inclusive).");
        }
        saveChanges();
    }

    private static void delete(String[] tokens) throws DukeException {
        if (tasks.size() == 0) {
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
            echo("Understood. I've removed the following task:\n\t"
                    + task + "\n"
                    + "Now you have " + tasks.size() + " task(s) in your list.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please specify a valid task number (between 1 to " + tasks.size() + " inclusive).");
        }
        saveChanges();
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
        echo("Got it. I've added this todo:\n\t"
                + todo + "\n"
                + "Now you have " + tasks.size() + " task(s) in your list.");
        saveChanges();
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
        echo("Got it. I've added this deadline:\n\t"
                + deadline + "\n"
                + "Now you have " + tasks.size() + " task(s) in your list.");
        saveChanges();
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
        echo("Got it. I've added this event:\n\t"
                + event + "\n"
                + "Now you have " + tasks.size() + " task(s) in your list.");
        saveChanges();
    }
}
