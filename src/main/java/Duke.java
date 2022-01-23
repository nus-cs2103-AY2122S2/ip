import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Runs a Personal Assistant Chatbot which helps users
 * keep track of tasks and other things. The current
 * iteration of the Chatbot is named Luca.
 */
public class Duke {

    /**
     * Logo of the Chatbot.
     */
    public static final String LOGO = "888      888     888  .d8888b.         d8888 \n"
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
    private static final String DIVIDER_LINE = "___________________"
            + "_________________________________________\n";

    /**
     * File used to store tasks.
     */
    private static final File TASK_FILE = new File("..\\..\\..\\data\\duke.txt");

    /**
     * List to store items. There will be no more than 100 items.
     */
    private static final ArrayList<Task> TASK_LIST = new ArrayList<>();


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
        for (int i = 0; i < TASK_LIST.size(); i++) {
            task = TASK_LIST.get(i);
            stringBuilder.append((i + 1) + "." + task.toString());
            if (i != TASK_LIST.size() - 1) {
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
        String temp = DIVIDER_LINE + input + "\n" + DIVIDER_LINE;
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
        Task task = TASK_LIST.get(point - 1);
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
        Task task = TASK_LIST.get(point - 1);
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
        TASK_LIST.add(task);
        return Duke.formatString("Got it! I've added this ToDo task:\n "
                + task.toString() + "\nNow you have " + TASK_LIST.size()
                + (TASK_LIST.size() == 1 ? " task" : " tasks") + " in the list.");
    }


    /**
     * Creates and add a Deadline task.
     * Default time t
     *
     * @param tokens input tokens to parse.
     * @return formatted string with Deadline Task.
     * @throws InvalidArgumentException if due date/time is empty.
     * @throws InvalidDateTimeFormatException if the date/time is in incorrect format.
     */
    private static String addDeadline(String[] tokens) throws InvalidArgumentException,
            InvalidDateTimeFormatException {
        StringBuilder stringBuilder = new StringBuilder();
        Boolean foundKeyword = false;
        LocalDateTime dateTime = null;
        String description = "";
        for (int i = 1; i < tokens.length; i++) {
            if (!foundKeyword) {
                if (tokens[i].equals("/by")) {
                    description = stringBuilder.toString();
                    foundKeyword = true;
                } else {
                    stringBuilder.append(tokens[i]);
                    if (i != tokens.length - 1) {
                        stringBuilder.append(" ");
                    }
                }
            } else {
                if (i == tokens.length - 1) {
                    dateTime = LocalDateTime.parse(tokens[i] + "T06:00:00");
                } else {
                    try {
                        int time = Integer.parseInt(tokens[i + 1]);
                        String hours;
                        String minutes;

                        if (time / 100 > 9) {
                            hours = Integer.toString(time / 100);
                        } else {
                            hours = "0" + Integer.toString(time / 100);
                        }

                        if (time % 100 > 9) {
                            minutes = Integer.toString(time % 100);
                        } else {
                            minutes = "0" + Integer.toString(time % 100);
                        }

                        dateTime = LocalDateTime.parse(tokens[i] + "T" + hours + ":" + minutes + ":00");
                    } catch (NumberFormatException | DateTimeParseException exception) {
                        throw new InvalidDateTimeFormatException("Please enter the date/time in "
                                + "the following format:\n yyyy-mm-dd HHMM in the 24 hour format");
                    }
                    i++;
                }
            }

        }
        if (!foundKeyword) {
            throw new InvalidArgumentException(":-( OOPS!!! Due date/time of deadline cannot be empty.");
        }
        Deadline task = new Deadline(description, dateTime);
        TASK_LIST.add(task);
        return Duke.formatString("Got it! I've added this Deadline task:\n "
                + task.toString() + "\nNow you have " + TASK_LIST.size()
                + (TASK_LIST.size() == 1 ? " task" : " tasks") + " in the list.");
    }

    /**
     * Creates and adds a Event.
     *
     * @param tokens input tokens to parse.
     * @return formatted string with Event task.
     * @throws IllegalArgumentException if Event date/time is empty.
     * @throws InvalidDateTimeFormatException if date/time is in the incorrect format.
     */
    private static String addEvent(String[] tokens) throws InvalidArgumentException,
            InvalidDateTimeFormatException {
        StringBuilder stringBuilder = new StringBuilder();
        Boolean foundKeyword = false;
        LocalDateTime start = null;
        LocalDateTime end = null;
        String description = "";
        for (int i = 1; i < tokens.length; i++) {
            if (!foundKeyword) {
                if (tokens[i].equals("/at")) {
                    description = stringBuilder.toString();
                    foundKeyword = true;
                } else {
                    stringBuilder.append(tokens[i]);
                    if (i != tokens.length - 1) {
                        stringBuilder.append(" ");
                    }
                }
            } else {

                    try {
                        int time = 0;
                        if (i != tokens.length) {
                            time = Integer.parseInt(tokens[i + 1]);
                        } else {
                            throw new InvalidDateTimeFormatException("Please enter the start and end date/time in "
                                    + "the following format:\n yyyy-mm-dd HHMM  yy-mm-dd HHMM in the 24 hour format");
                        }

                        String hours;
                        String minutes;

                        if (time / 100 > 9) {
                            hours = Integer.toString(time / 100);
                        } else {
                            hours = "0" + Integer.toString(time / 100);
                        }

                        if (time % 100 > 9) {
                            minutes = Integer.toString(time % 100);
                        } else {
                            minutes = "0" + Integer.toString(time % 100);
                        }

                        if (start == null) {
                            start = LocalDateTime.parse(tokens[i] + "T" + hours + ":" + minutes + ":00");
                        } else {
                            end = LocalDateTime.parse(tokens[i] + "T" + hours + ":" + minutes + ":00");
                        }

                    } catch (NumberFormatException | DateTimeParseException exception) {
                        throw new InvalidDateTimeFormatException("Please enter the start and end date/time in "
                                + "the following format:\n yyyy-mm-dd HHMM  yy-mm-dd HHMM in the 24 hour format");
                    }
                    i++;
            }

        }
        if (!foundKeyword) {
            throw new InvalidArgumentException(":-( OOPS!!! Start-End date/time of event "
                    + "cannot be empty.");
        }
        Event task = new Event(description, start, end);
        TASK_LIST.add(task);
        return Duke.formatString("Got it! I've added this Event task:\n "
                + task.toString() + "\nNow you have " + TASK_LIST.size()
                + (TASK_LIST.size() == 1 ? " task" : " tasks") + " in the list.");
    }

    /**
     * Deletes the task from the list.
     *
     * @param point 1-based index of the task in the list.
     * @return formatted string stating task is deleted.
     */
    private static String deleteTask(int point) {
        Task task = TASK_LIST.get(point - 1);
        TASK_LIST.remove(point - 1);
        return Duke.formatString("Noted. I've removed this task:\n "
                + task.toString() + "\nNow you have " + TASK_LIST.size()
                + (TASK_LIST.size() == 1 ? " task" : " tasks") + " in the list.");
    }


    /**
     * Runs the Personal Assistant Chatbot where
     * the user can input commands.
     *
     * @param args initial arguments.
     */
    public static void main(String[] args) {

        FileHandler fileHandler = new FileHandler(TASK_LIST);

        String startPrompt = "Hi! I'm Luca\n"
                + "How may I help you?\n";

        System.out.println(Duke.formatString("\n" + LOGO + "\n"
                + startPrompt));

        try {
            fileHandler.readFromFile();
        } catch (DukeException exception) {
            System.out.println(Duke.formatString(exception.getMessage()));
        }


        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] tokens = input.split("\\s+");

        while (!tokens[0].equals("bye")) {
            try {
                if (tokens[0].equals("list")) {
                    System.out.println(Duke.listToString());
                } else if (tokens[0].equals("mark")) {
                    if (tokens.length < 2) {
                        throw new InvalidArgumentException(":-( OOPS!!! Please indicate "
                                + "the task to be marked.");
                    }
                    try {
                        System.out.println(Duke.markTask(Integer.parseInt(tokens[1])));
                    } catch (NumberFormatException exception) {
                        throw new InvalidArgumentException(":-( OOPS!!! Please input an "
                                + "integer with the mark command.");
                    }
                    fileHandler.saveToFile();
                } else if (tokens[0].equals("unmark")) {
                    if (tokens.length < 2) {
                        throw new InvalidArgumentException(":-( OOPS!!! Please indicate "
                                + "the task to be unmasked.");
                    }
                    try {
                        System.out.println(Duke.unmarkTask(Integer.parseInt(tokens[1])));
                    } catch (NumberFormatException exception) {
                        throw new InvalidArgumentException(":-( OOPS!!! Please input an "
                                + "integer with the unmark command.");
                    }
                    fileHandler.saveToFile();
                } else if (tokens[0].equals("todo")) {
                    if (tokens.length < 2) {
                        throw new InvalidArgumentException(":-( OOPS!!! The description of "
                                + "ToDo cannot be empty.");
                    }
                    System.out.println(Duke.addToDo(tokens));
                    fileHandler.saveToFile();
                } else if (tokens[0].equals("deadline")) {
                    if (tokens.length < 2) {
                        throw new InvalidArgumentException(":-( OOPS!!! The description of "
                                + "Deadline cannot be empty.");
                    }
                    System.out.println(Duke.addDeadline(tokens));
                    fileHandler.saveToFile();
                } else if (tokens[0].equals("event")) {
                    if (tokens.length < 2) {
                        throw new InvalidArgumentException(":-( OOPS!!! The description of "
                                + "Event cannot be empty.");
                    }
                    System.out.println(Duke.addEvent(tokens));
                    fileHandler.saveToFile();
                } else if (tokens[0].equals("delete")) {
                    if (tokens.length < 2) {
                        throw new InvalidArgumentException(":-( OOPS!!! Please indicate "
                                + "the task to be deleted.");
                    }
                    try {
                        System.out.println(Duke.deleteTask(Integer.parseInt(tokens[1])));
                    } catch (NumberFormatException exception) {
                        throw new InvalidArgumentException(":-( OOPS!!! Please input an "
                                + "integer with the delete command.");
                    }
                    fileHandler.saveToFile();
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
