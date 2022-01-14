import java.util.ArrayList;
import java.util.Scanner;

/**
 * Duke class represents a Personal Assistant Chatbot that
 * helps a person to keep track of various things.
 */
public class Duke {
    static String lineBreak = "____________________________________________________________\n";
    static String catFace = " =^_^=\n";
    static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Prints the greeting and logo.
     */
    public static void greet() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____      /\\_/\\           ___\n"
                + "| | | | | | | |/ / _      = o_o =_______    \\ \\  -Julie Rhodes-\\\n"
                + "| |_| | |_| |   <  __/     __^      __(  \\.__) )\n"
                + "|____/ \\__,_|_|\\_\\___| (@)<_____>__(_____)____/\n";
        System.out.print(logo + lineBreak + "Meow! I'm Duke" + catFace
                + "What can I do for you?\n" + lineBreak);
    }

    /**
     * Prints the farewell.
     */
    public static void exit() {
        System.out.print(lineBreak + "Bye. Meow!" + catFace + lineBreak);
    }

    /**
     * Processes the command from the user.
     * @param command User command.
     * @param description Task description.
     */
    public static void processCommand(String command, String description) {
        int index;
        Task t;

        switch (command) {
        case "list":
            System.out.print(lineBreak);
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
            System.out.print(lineBreak);
            break;
        case "mark":
            try {
                index = Integer.parseInt(description.trim());
                tasks.get(index - 1).markAsDone();
                System.out.print(lineBreak + "Meow! Task is done!" + catFace
                        + tasks.get(index - 1) + "\n" + lineBreak);
            } catch (NumberFormatException e) {
                System.out.print(lineBreak + "Meow! Enter a valid task!\n" + lineBreak);
            } catch (NullPointerException | IndexOutOfBoundsException e) {
                System.out.print(lineBreak + "Meow! Task does not exist!\n" + lineBreak);
            }
            break;
        case "unmark":
            try {
                index = Integer.parseInt(description.trim());
                tasks.get(index - 1).unmarkAsDone();
                System.out.print(lineBreak + "Meow! Task is not done!" + catFace
                        + tasks.get(index - 1) + "\n" + lineBreak);
            } catch (NumberFormatException e) {
                System.out.print(lineBreak + "Meow! Enter a valid task!\n" + lineBreak);
            } catch (NullPointerException | IndexOutOfBoundsException e) {
                System.out.print(lineBreak + "Meow! Task does not exist!\n" + lineBreak);
            }
            break;
        case "delete":
            try {
                index = Integer.parseInt(description.trim());
                t = tasks.get(index - 1);
                tasks.remove(index - 1);
                System.out.print(lineBreak + "Meow! Task is removed!\n" + t + "\n"
                        + "Number of tasks in list: " + tasks.size() + catFace + lineBreak);
            } catch (NumberFormatException e) {
                System.out.print(lineBreak + "Meow! Enter a valid task!\n" + lineBreak);
            } catch (NullPointerException | IndexOutOfBoundsException e) {
                System.out.print(lineBreak + "Meow! Task does not exist!\n" + lineBreak);
            }
            break;
        default:
            try {
                switch (command) {
                case "deadline":
                    if (description.isBlank()) {
                        throw new DukeException(ExceptionType.EMPTYDESC);
                    }

                    if (!description.contains("/by")) {
                        throw new DukeException(ExceptionType.INVALIDDATE);
                    }

                    String deadline = description.split("/by", 2)[1].trim();
                    description = description.split("/by", 2)[0].trim();

                    if (deadline.isBlank()) {
                        throw new DukeException(ExceptionType.INVALIDDATE);
                    }

                    t = new Deadline(description, deadline);
                    break;
                case "event":
                    if (description.isBlank()) {
                        throw new DukeException(ExceptionType.EMPTYDESC);
                    }

                    if (!description.contains("/at")) {
                        throw new DukeException(ExceptionType.INVALIDDATE);
                    }

                    String period = description.split("/at", 2)[1].trim();
                    description = description.split("/at", 2)[0].trim();

                    if (period.isBlank()) {
                        throw new DukeException(ExceptionType.INVALIDDATE);
                    }

                    t = new Event(description, period);
                    break;
                case "todo":
                    if (description.isBlank()) {
                        throw new DukeException(ExceptionType.EMPTYDESC);
                    }

                    t = new Todo(description.trim());
                    break;
                default:
                    System.out.print(lineBreak + "Meow? I don't know what that means.\n" + lineBreak);
                    return;
                }

                tasks.add(t);
                System.out.print(lineBreak + "Meow! Task is added!\n" + t + "\n"
                        + "Number of tasks in list: " + tasks.size() + catFace + lineBreak);
            } catch (DukeException e) {
                e.EmptyDescriptionException();
                e.InvalidDateException();
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        greet();

        String input = sc.nextLine().strip();
        while (!input.equals("bye")) {
            try {
                if (input.isBlank()) {
                    throw new DukeException(ExceptionType.EMPTYINPUT);
                }

                String command = (input + " ").split(" ")[0];
                String description = (input + " ").split(" ", 2)[1];
                processCommand(command, description);
            } catch (DukeException e) {
                e.EmptyInputException();
            } finally {
                input = sc.nextLine().strip();
            }
        }

        exit();
        sc.close();
    }
}