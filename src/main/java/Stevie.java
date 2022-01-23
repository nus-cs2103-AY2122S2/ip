import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Stevie is a class that serves as a user interface to allow access to an underlying
 * task manager. Users can use Stevie to add in their upcoming tasks/events/deadlines,
 * and to mark them as completed when necessary.
 */
public class Stevie {
    private static TaskList tl;

    public static void main(String[] args) {
        tl = new TaskList(TaskDataHandler.loadTasks());
        Scanner sc = new Scanner(System.in);

        greet();

        String userIn = "";
        String out;
        do {
            if (userIn.length() > 0) {
                try {
                    out = processUserInput(userIn);
                    speech(out);
                } catch (StevieException ex) {
                    speech(ex.getMessage());
                }
            }
            userIn = sc.nextLine();
        } while (!userIn.equals("bye"));
        speech("Good bye! Hope to see you again!");
    }

    /**
     * Greets the user.
     */
    private static void greet() {
        // Logo generated from:
        // https://patorjk.com/software/taag/#p=display&f=Graffiti&t=Type%20Something%20
        String logo = "  .--.--.       ___\n" +
                " /  /    '.   ,--.'|_                        ,--,\n" +
                "|  :  /`. /   |  | :,'                     ,--.'|\n" +
                ";  |  |--`    :  : ' :                .---.|  |,\n" +
                "|  :  ;_    .;__,'  /     ,---.     /.  ./|`--'_       ,---.\n" +
                " \\  \\    `. |  |   |     /     \\  .-' . ' |,' ,'|     /     \\\n" +
                "  `----.   \\:__,'| :    /    /  |/___/ \\: |'  | |    /    /  |\n" +
                "  __ \\  \\  |  '  : |__ .    ' / |.   \\  ' .|  | :   .    ' / |\n" +
                " /  /`--'  /  |  | '.'|'   ;   /| \\   \\   ''  : |__ '   ;   /|\n" +
                "'--'.     /   ;  :    ;'   |  / |  \\   \\   |  | '.'|'   |  / |\n" +
                "  `--'---'    |  ,   / |   :    |   \\   \\ |;  :    ;|   :    |\n" +
                "               ---`-'   \\   \\  /     '---\" |  ,   /  \\   \\  /\n" +
                "                         `----'             ---`-'    `----'   ";
        System.out.println("Hello I'm\n" + logo);
        System.out.println("Tell me about your upcoming activities!");
        System.out.println("Input \"help\" for instructions.");
    }

    /**
     * Helper text on the special keywords to instruct Stevie.
     *
     * @return instruction for user
     */
    private static String help() {
        return "\"list\": to display your activities.\n" +
                "\"bye\": to end our session.\n" +
                "\"mark <i>\" to mark the i-th task as done.\n" +
                "\"unmark <i>\" to unmark the i-th task as done.\n" +
                "\"delete <i>\" to delete the i-th task.\n" +
                "\"todo <task_name>\" to add a todo task.\n" +
                "\"deadline <task_name> /by <date>\" to add a deadline.\n" +
                "\"event <event_name> /at <date>\" to add an event.\n" +
                "Date should in format of dd/mm/yyyy HH:mm";
    }

    /**
     * Processes user's input and executes the instruction.
     *
     * @param userIn user's input
     * @return response string to user
     * @throws StevieException if user input is invalid
     */
    private static String processUserInput(String userIn) throws StevieException {
        if (userIn.equals("list")) {
            return tl.toString();
        } else if (userIn.equals("help")) {
            return help();
        } else if (Pattern.matches("^mark\\s\\d+", userIn)) {
            return "This activity is marked as done:\n"
                    + tl.markDone(Integer.parseInt(userIn
                    .replaceAll("[^\\d.]", "")) - 1)
                    .toString();
        } else if (Pattern.matches("^unmark\\s\\d+", userIn)) {
            return "This activity is unmarked as done:\n" +
                    tl.markUndone(Integer.parseInt(userIn
                            .replaceAll("[^\\d.]", "")) - 1)
                            .toString();
        } else if (Pattern.matches("^delete\\s\\d+", userIn)) {
            return tl.delete(Integer.parseInt(userIn
                    .replaceAll("[^\\d.]", "")) - 1);
        } else {
            return processAddTask(userIn);
        }
    }

    /**
     * Processes user's attempt to add a task. Type of tasks is identifiable by the
     * first word in the user's input. Task entry should match the task's format, in
     * order to be added successfully.
     *
     * @param userIn user's input
     * @return response string to user
     * @throws StevieException if user input is invalid
     */
    private static String processAddTask(String userIn) throws StevieException {
        if (Pattern.matches("^todo\\s(.*?)", userIn)) {
            String s = userIn.replace("todo ", "").trim();
            if (s.length() == 0) {
                throw new StevieException("Todo task requires a task name!");
            } else {
                return tl.add(TaskType.Todo, s);
            }
        } else if (Pattern.matches("^deadline\\s(.*s?)\\s/by\\s(.*s?)", userIn)) {
            String[] split = userIn
                    .replace("deadline ", "")
                    .split("\\s/by\\s", 2);
            split[0] = split[0].trim();
            split[1] = split[1].trim();
            if (split[0].length() == 0 || split[1].length() == 0) {
                throw new StevieException("Deadline task requires a task name and a date!");
            } else {
                Date date;
                try {
                    date = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(split[1]);
                } catch (ParseException ex) {
                    throw new StevieException("Date format is unacceptable!");
                }
                return tl.add(TaskType.Deadline, split[0], date);
            }
        } else if (Pattern.matches("^event\\s(.*s?)\\s/at\\s(.*s?)", userIn)) {
            String[] split = userIn
                    .replace("event ", "")
                    .split("\\s/at\\s", 2);
            split[0] = split[0].trim();
            split[1] = split[1].trim();
            if (split[0].length() == 0 && split[1].length() == 0) {
                throw new StevieException("Event task requires a task name and a date!");
            } else {
                Date date;
                try {
                    date = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(split[1]);
                } catch (ParseException ex) {
                    throw new StevieException("Date format is unacceptable!");
                }
                return tl.add(TaskType.Event, split[0], date);
            }
        } else {
            throw new StevieException("Oops! Your instructions were unclear!");
        }
    }

    /**
     * Prints Stevie's formatted speech.
     *
     * @param text Stevie's speech
     */
    private static void speech(String text) {
        System.out.println("____________________________");
        System.out.println(text);
        System.out.println("____________________________");
    }
}
