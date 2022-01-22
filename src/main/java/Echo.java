import java.util.ArrayList;
import java.util.Scanner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Echo, a Personal Assistant Chatbot that helps a person to keep track of various things.
 */
public class Echo {

    /** ArrayList of task. */
    private static final ArrayList<Task> TASKS = new ArrayList<>();

    public static void main(String[] args) {
        // Initialise scanner to read inputs.
        Scanner sc = new Scanner(System.in);

        // Welcomes user with a hello message.
        hello();

        // Read inputs.
        String s = sc.nextLine();
        while (!s.equals("bye")) {
            try {
                readInput(s);
            } catch (EchoException e) {
                printFormat(e.getMessage());
            } finally {
                s = sc.nextLine();
            }
        }
        // Prints a goodbye message.
        goodbye();

        // Close scanner.
        sc.close();
    }

    /**
     * Reads input string and determines action to perform.
     *
     * @param input Input string from user.
     * @throws EchoException If input is missing details or badly formatted.
     */
    public static void readInput(String input) throws EchoException {

        try {
            // Split input based on white spaces.
            String[] splitSpace = input.split(" ");
            String command = splitSpace[0];

            // Message to be printed, StringBuilder used for concatenation in O(1).
            StringBuilder message = new StringBuilder();

            // Switch to identify the command and perform the appropriate operations.
            switch (command) {
            case "commands":
                printCommands();
                break;
            case "list":
                if (TASKS.size() == 0) {
                    // Task list is empty.
                    printFormat("    Task list is empty!");
                } else {
                    // Task list is not empty. Prints all tasks.
                    for (int i = 0; i < TASKS.size(); i++) {
                        message.append(String.format("    %d. %s \n", i + 1, TASKS.get(i)));
                    }
                    message.setLength(message.length() - 2);
                    printFormat(message.toString());
                }
                break;
            case "mark":
            case "unmark":
            case "delete":

                // If second input (task number) is not specified, throw EchoException.
                if (splitSpace.length == 1) {
                    throw new EchoException("    Please specify the task number. Eg. mark 1, unmark 1, delete 1");
                }

                // Parse second input into an int, throws NumberFormatException if it's not an int.
                int taskIndex = Integer.parseInt(splitSpace[1]) - 1;

                // Condition to identify the command.
                if (command.equals("mark")) {
                    // Mark the task specified.
                    TASKS.get(taskIndex).mark();
                    message.append("    Nice! The task is marked as done: \n");
                } else if (command.equals("unmark")) {
                    // Unmark the task specified.
                    TASKS.get(taskIndex).unmark();
                    message.append("    OK! The task is unmarked. \n");
                } else {
                    // Delete the task specified.
                    message.append("    Noted. I've removed the task: \n");
                    message.append(String.format("      %d. %s \n", taskIndex + 1, TASKS.get(taskIndex).toString()));
                    TASKS.remove(taskIndex);
                }

                // If command is "delete", append number of task remaining; Otherwise, append status of current task.
                if (command.equals("delete")) {
                    message.append(String.format("    Now you have %d tasks in the list.", TASKS.size()));
                } else {
                    message.append(taskStatus(taskIndex));
                }

                // Print message.
                printFormat(message.toString());

                break;
            case "todo":
            case "deadline":
            case "event":

                // Split input based on /.
                String[] splitSlash = input.split("/");

                // Description of task.
                String desc = splitSlash[0].substring(command.length() + 1);

                String dateTimeString;
                LocalDateTime localDateTime;

                // If second input (description) is not specified, throw EchoException.
                if (splitSpace.length == 1) {
                    throw new EchoException(String.format("    The description of a %s cannot be empty.", input));
                }

                // Condition to identify the command.
                if (command.equals("todo")) {
                    // Add TodoTask.
                    TASKS.add(new TodoTask(desc));
                } else {
                    // "/" not specified in input.
                    if (!input.contains("/")) {
                        throw new EchoException("    Please specify the time of the task: \n"
                                + "    deadline <description> /by <time> \n"
                                + "    event <description> /at <time>");
                    }

                    dateTimeString = splitSlash[1].substring(splitSlash[1].indexOf(" ") + 1);
                    localDateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-M-d HHmm"));

                    if (command.equals("deadline")) {
                        // If /by is not specified, throw EchoException.
                        if (!input.contains("/by")) {
                            throw new EchoException("    Please specify the deadline of the task. "
                                    + "E.g. deadline return book /by 2019-10-15 1800");
                        }

                        // Add DeadlineTask.
                        TASKS.add(new DeadlineTask(desc, localDateTime));
                    } else {
                        // If /at is not specified, throw EchoException.
                        if (!input.contains("/at")) {
                            throw new EchoException("    Please specify the date and time of the event. "
                                    + "E.g. event meeting /at 2019-10-15 1800");
                        }

                        // Add EventTask task.
                        TASKS.add(new EventTask(desc, localDateTime));
                    }
                }

                // Message to inform user of successful execution.
                message.append("    Got it. I've added this task: \n");
                message.append(taskStatus(TASKS.size() - 1)).append("\n");
                message.append(String.format("    Now you have %d tasks in the list.", TASKS.size()));

                // Print message.
                printFormat(message.toString());
                break;
            default:
                // Input not recognised.
                throw new EchoException("    I'm sorry, but I don't know what that means :-(\n"
                        + "    Type <commands> for a list of available commands");
            }
        } catch (NumberFormatException nfe) {
            // Second input provided by user is not an int.
            printFormat("    Second input must be an integer. Eg. mark 1, unmark 1, delete 1");
        } catch (ArrayIndexOutOfBoundsException e) {
            // Task number provided by user does not exist in TASKS.
            printFormat("    Task does not exist!");
        } catch (DateTimeParseException e) {
            printFormat("    Wrong format for date and time. Follow the <yyyy-mm-dd> <24hr time> format. \n"
                    + "    E.g. 2019-10-15 1800");
        }
    }

    /**
     * Prints string representation of task index and description.
     *
     * @param i Task index.
     *
     * @return String representing task status.
     */
    private static String taskStatus(int i) {
        return String.format("          %d. %s", i+1, TASKS.get(i).toString());
    }

    /**
     * Prints the welcome message.
     */
    private static void hello() {
        String logo = "U _____ u    ____    _   _      U  ___ u \n"
                + "\\| ___\"|/ U /\"___|  |'| |'|      \\/\"_ \\/ \n"
                + " |  _|\"   \\| | u   /| |_| |\\     | | | | \n"
                + " | |___    | |/__  U|  _  |u .-,_| |_| | \n"
                + " |_____|    \\____|  |_| |_|   \\_)-\\___/  \n"
                + " <<   >>   _// \\\\   //   \\\\        \\\\    \n"
                + "(__) (__) (__)(__) (_\") (\"_)      (__) \n";
        System.out.print(logo);
    }

    /**
     * Prints the exit message.
     */
    private static void goodbye() {
        printFormat("        Goodbye!");
    }

    /**
     * Prints response with line dividers.
     *
     * @param s String to be printed.
     */
    private static void printFormat(String s) {
        System.out.println("    ____________________________________________________________");
        System.out.println(s);
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints user commands.
     */
    private static void printCommands() {
        printFormat("    Commands: \n"
                + "    list                                | List current tasks. \n"
                + "    todo <description>                  | Adds a todo task. \n"
                + "    deadline <description> /by <time>   | Adds a deadline task to be done before a specified time. \n"
                + "    event <description> /at <time>      | Adds an event task that occurs at a specified time. \n"
                + "    mark <task number>                  | Marks task as completed. \n"
                + "    unmark <task number>                | Unmark task as uncompleted. \n"
                + "    delete <task number>                | Deletes task. \n"
                + "    bye                                 | exit. ");
    }


}
