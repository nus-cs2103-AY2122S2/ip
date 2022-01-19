import java.util.ArrayList;
import java.util.Scanner;

/**
 *  Runs a Personal Assistant Chatbot which helps users
 *  keep track of tasks and other things. The current
 *  iteration of the Chatbot is named Luca.
 */
public class Duke {

    /**
     * Logo of the Chatbot.
     */
   public static final String logo = "888      888     888  .d8888b.         d8888 \n"
           + "888      888     888 d88P  Y88b       d88888 \n"
           + "888      888     888 888    888      d88P888 \n"
           + "888      888     888 888            d88P 888 \n"
           + "888      888     888 888           d88P  888 \n"
           + "888      888     888 888    888   d88P   888 \n"
           + "888      Y88b. .d88P Y88b  d88P  d8888888888 \n"
           + "88888888  \"Y88888P\"   \"Y8888P\"  d88P     888 \n";

    /**
     * The divder line used to wrap string before output.
     */
    private static final String dividerLine = "___________________"
            + "_________________________________________\n";

    /**
     * List to store items. There will be no more than 100 items.
     */
    private static ArrayList<Task> list = new ArrayList<>();


    /**
     * Returns the string containing the task text on the list with
     * formatting.
     *
     * @return formatted string with list text
     */
    private static String listToString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the tasks in your list:\n");
        Task task;
        for (int i = 0; i < list.size(); i++) {
            task = list.get(i);
            stringBuilder.append((i + 1) +  "." + task.toString());
            if (i != list.size() - 1) {
                stringBuilder.append("\n");
            }
        }
        return formatString(stringBuilder.toString());
    }

    /**
     * Formats the string by indenting with 4 spaces
     * and wrapping the string with divider lines.
     *
     * @param input string to be formatted.
     * @return indented string with divider lines.
     */
    private static String formatString(String input) {
        String temp = dividerLine + input + "\n" + dividerLine;
        String indented = temp.replaceAll("(?m)^", "    ");
        return indented;
    }

    /**
     * Changes the task status to done.
     *
     * @param point 1-based index of the task in the list.
     * @return formatted success response string.
     * @see Task
     */
    private static String markTask(int point) {
       Task task = list.get(point - 1);
       task.markAsDone();
       return Duke.formatString("Great! I have marked this task as done:\n "
               + task.toString());
    }

    /**
     * Changes the task status to not done.
     *
     * @param point 1-based index of the task in the list.
     * @return formatted string stating task not done.
     * @see Task
     */
    private static String unmarkTask(int point) {
        Task task = list.get(point - 1);
        task.unmarkAsDone();
        return Duke.formatString("OK, I've marked this task as not done yet:\n "
                + task.toString());
    }

    /**
     * Creates and adds a ToDo task to the list.
     *
     * @param tokens input tokens to parse.
     * @return formatted string with ToDo task.
     */
    private static String addToDo(String[] tokens) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < tokens.length; i++) {
            stringBuilder.append(tokens[i]);
            if (i != tokens.length - 1) {
                stringBuilder.append(" ");
            }
        }
        ToDo task = new ToDo(stringBuilder.toString());
        list.add(task);
        return Duke.formatString("Got it! I've added this ToDo task:\n " +
                task.toString() +"\nNow you have " + list.size()
                + (list.size() <= 1 ? " task" : " tasks") + " in the list.");
    }


    /**
     * Creates and add a Deadline task.
     *
     * @param tokens input tokens to parse.
     * @return formatted string with Deadline Task.
     * @throws InvalidArgumentException if due date/time is empty.
     */
    private static String addDeadline(String[] tokens) throws InvalidArgumentException {
        StringBuilder stringBuilder = new StringBuilder();
        String by = "";
        String description = "";
        for (int i = 1; i < tokens.length; i++) {
            stringBuilder.append(tokens[i]);
            if (i + 1 < tokens.length) {
                if (tokens[i + 1].equals("/by")) {
                   description = stringBuilder.toString();
                   stringBuilder = new StringBuilder();
                   i++;
               } else {
                   stringBuilder.append(" ");
               }
            }
        }
        by = stringBuilder.toString();
        if (by.equals("")) {
            throw new InvalidArgumentException("☹ OOPS!!! Due date/time of deadline cannot empty.");
        }
        Deadline task = new Deadline(description, by);
        list.add(task);
        return Duke.formatString("Got it! I've added this Deadline task:\n " +
                task.toString() +"\nNow you have " + list.size()
                        + (list.size() <= 1 ? " task" : " tasks") + " in the list.");
    }

    /**
     * Creates and adds a Event.
     *
     * @param tokens input tokens to parse.
     * @return formatted string with Event task.
     * @throws IllegalArgumentException if Event date/time is empty.
     */
    private static String addEvent(String[] tokens) throws InvalidArgumentException{
        StringBuilder stringBuilder = new StringBuilder();
        String at = "";
        String description = "";
        for (int i = 1; i < tokens.length; i++) {
            stringBuilder.append(tokens[i]);
            if (i + 1 < tokens.length) {
                if (tokens[i + 1].equals("/at")) {
                    description = stringBuilder.toString();
                    stringBuilder = new StringBuilder();
                    i++;
                } else {
                    stringBuilder.append(" ");
                }
            }
        }
        at = stringBuilder.toString();
        if (at.equals("")) {
            throw new InvalidArgumentException("☹ OOPS!!! Start and End date/time of event " +
                    "cannot empty.");
        }
        Event task = new Event(description, at);
        list.add(task);
        return Duke.formatString("Got it! I've added this Event task:\n " +
                task.toString() +"\nNow you have " + list.size()
                + (list.size() <= 1 ? " task" : " tasks") + " in the list.");
    }


    /**
     * Runs the Personal Assistant Chatbot where
     * the user can input commands.
     *
     * @param args initial arguments.
     */
    public static void main(String[] args) {

        String startPrompt = "Hi! I'm Luca\n"
                + "How may I help you?\n";

        System.out.println(Duke.formatString("\n" + logo + "\n"
                + startPrompt));

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] tokens = input.split("\\s+");

        // Exit loop only after the user enters "Bye"
        while (!tokens[0].equals("bye")) {
            try {
                if (tokens[0].equals("list")) {
                    System.out.println(Duke.listToString());
                } else if (tokens[0].equals("mark")){
                    if (tokens.length < 2) {
                        throw new InvalidArgumentException("☹ OOPS!!! Please indicate "
                                + "the task to be marked.");
                    }
                    try {
                        System.out.println(Duke.markTask(Integer.parseInt(tokens[1])));
                    } catch (NumberFormatException exception) {
                        throw new InvalidArgumentException("☹ OOPS!!! Please input an "
                                + "integer with the mark command.");
                    }
                } else if (tokens[0].equals("unmark")) {
                    if (tokens.length < 2) {
                        throw new InvalidArgumentException("☹ OOPS!!! Please indicate "
                                + "the task to be unmasked.");
                    }
                    try {
                        System.out.println(Duke.unmarkTask(Integer.parseInt(tokens[1])));
                    } catch (NumberFormatException exception) {
                        throw new InvalidArgumentException("☹ OOPS!!! Please input an "
                                + "integer with the unmark command.");
                    }
                } else if (tokens[0].equals("todo")) {
                    if (tokens.length < 2) {
                        throw new InvalidArgumentException("☹ OOPS!!! The description of "
                                + "ToDo cannot be empty.");
                    }
                    System.out.println(Duke.addToDo(tokens));
                } else if (tokens[0].equals("deadline")) {
                    if (tokens.length < 2) {
                        throw new InvalidArgumentException("☹ OOPS!!! The description of "
                                + "Deadline cannot be empty.");
                    }
                    System.out.println(Duke.addDeadline(tokens));
                } else if (tokens[0].equals("event")) {
                    if (tokens.length < 2) {
                        throw new InvalidArgumentException("☹ OOPS!!! The description of "
                                + "Event cannot be empty.");
                    }
                    System.out.println(Duke.addEvent(tokens));
                } else {
                    throw new UnkownCommandException();
                }
            } catch (DukeException dukeException) {
                System.out.println(Duke.formatString(dukeException.getMessage()));
            }

            input = scanner.nextLine();
            tokens = input.split("\\s+");
        }

        System.out.println(Duke.formatString("Bye. See you again soon!"));
    }
}
