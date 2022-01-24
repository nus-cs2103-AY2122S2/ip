import java.util.ArrayList;
import java.util.Scanner;

/**
 * Chatbot that supports tracking tasks as added by the user. Created as part of CS2103T.
 *
 * @author Jet Tan
 */
public class Duke {
    private static final String LOGO =
            "   ___      _  ______       _   \n" +
                    "  |_  |    | | | ___ \\     | |  \n" +
                    "    | | ___| |_| |_/ / ___ | |_ \n" +
                    "    | |/ _ \\ __| ___ \\/ _ \\| __|\n" +
                    "/\\__/ /  __/ |_| |_/ / (_) | |_ \n" +
                    "\\____/ \\___|\\__\\____/ \\___/ \\__|\n";
    private static final String BORDER = "________________________________\n";
    private static final ArrayList<Task> TASKS = new ArrayList<>();

    /**
     * Greets the user.
     */
    private static void greet() {
        System.out.println(BORDER + LOGO + BORDER);
        System.out.println("How may I help you today? Please enter your command:");
    }

    /**
     * Exits the program.
     */
    private static void exit() {
        System.out.println(BORDER + "Bye!\n" + BORDER);
        System.exit(0);
    }

    /**
     * Marks the specified task as done.
     */
    private static void mark(int tasknum) {
        Task t = TASKS.get(tasknum - 1);
        System.out.println(BORDER + "Well done! I've marked this task as done: \n");
        t.markAsDone();
        System.out.println(t + "\n" + BORDER);
    }

    /**
     * Unmarks the specified task.
     */
    private static void unmark(int tasknum) {
        Task t = TASKS.get(tasknum - 1);
        System.out.println(BORDER + "No problem, I've marked this task as undone: \n");
        t.unmarkAsDone();
        System.out.println(t + "\n" + BORDER);
    }

    /**
     * Returns the string to be printed on a successful add operation.
     */
    private static String successMessage(Task t) {
        return BORDER + "Got it. I've added this task:\n" + t + "\n" +
                "Now you have " + TASKS.size() + " task(s) in the list.\n" + BORDER;
    }

    /**
     * Deletes the specified task.
     */
    private static void delete(int tasknum) {
        Task t = TASKS.get(tasknum - 1);
        System.out.println(BORDER + "No problem, I've deleted the following task:\n" + t);
        TASKS.remove(tasknum - 1);
        System.out.println("There are now " + TASKS.size() + " task(s) remaining.\n" + BORDER);
    }

    /**
     * Processes the input.
     */
    private static void process(String input) throws DukeException {
        String[] arr = input.split(" ");
        String command = arr[0]; // first word of the user input
        switch (command) {
        case "bye":
            exit();
            break;
        case "list":
            if (TASKS.size() == 0) {
                System.out.println(BORDER + "The list is empty. Why not add some tasks?\n" + BORDER);
            } else {
                StringBuilder listString = new StringBuilder();
                for (int i = 0; i < TASKS.size(); i++) {
                    Task t = TASKS.get(i);
                    listString.append(i + 1).append(".").append(t.toString()).append("\n");
                }
                System.out.println(BORDER + listString + BORDER);
            }
            break;
        case "mark": {
            int num = Integer.parseInt(arr[1]);
            if (num > TASKS.size()) {
                throw new InvalidInputException(BORDER + "The specified task does not exist.\n" + BORDER);
            } else if (num < 1) {
                throw new InvalidInputException(BORDER + "Selection must be positive.\n" + BORDER);
            }
            mark(num);
            break;
        }
        case "unmark": {
            int num = Integer.parseInt(arr[1]);
            if (num > TASKS.size()) {
                throw new InvalidInputException(BORDER + "The specified task does not exist.\n" + BORDER);
            } else if (num < 1) {
                throw new InvalidInputException(BORDER + "Selection must be positive.\n" + BORDER);
            }
            unmark(num);
            break;
        }
        case "todo": {
            String desc = input.replaceFirst("todo", "").trim();
            Todo newTodo = new Todo(desc);
            if (desc.equals("")) {
                throw new EmptyDescException(BORDER + "Todo description cannot be empty.\n" + BORDER);
            }
            TASKS.add(newTodo);
            System.out.println(successMessage(newTodo));
            break;
        }
        case "event": {
            if (!input.contains("/at")) {
                throw new InvalidInputException("Usage: event <description> /at <time>");
            }
            String[] descTimePair = input.replaceFirst("event", "").trim().split("/at");
            if (descTimePair.length < 2) {
                throw new InvalidInputException("Usage: event <description> /at <time>");
            }
            String desc = descTimePair[0];
            if (desc.equals("")) {
                throw new EmptyDescException(BORDER + "Event description cannot be empty.\n" + BORDER);
            }
            String time = descTimePair[1];
            Event newEvent = new Event(desc, time);
            TASKS.add(newEvent);
            System.out.println(successMessage(newEvent));
            break;
        }
        case "deadline": {
            if (!input.contains("/by")) {
                throw new InvalidInputException("Usage: deadline <description> /by <time>");
            }
            String[] descTimePair = input.replaceFirst("deadline", "").trim().split("/by");
            if (descTimePair.length < 2) {
                throw new InvalidInputException("Usage: deadline <description> /by <time>");
            }
            String desc = descTimePair[0];
            if (desc.equals("")) {
                throw new EmptyDescException(BORDER + "Deadline description cannot be empty.\n" + BORDER);
            }
            String time = descTimePair[1];
            Deadline newDeadline = new Deadline(desc, time);
            TASKS.add(newDeadline);
            System.out.println(successMessage(newDeadline));
            break;
        }
        case "delete": {
            int num = Integer.parseInt(arr[1]);
            if (num > TASKS.size()) {
                throw new InvalidInputException(BORDER + "The specified task does not exist.\n" + BORDER);
            } else if (num < 1) {
                throw new InvalidInputException(BORDER + "Selection must be positive.\n" + BORDER);
            }
            delete(num);
            break;
        }
        default:
            throw new UnknownCommandException(BORDER + "I'm sorry, but I don't know what that means.\n" + BORDER);
        }
    }

    /**
     * Driver method of the chatbot.
     */
    public static void main(String[] args) {
        greet();
        Scanner s = new Scanner(System.in);
        while (s.hasNextLine()) {
            String input = s.nextLine().toLowerCase().trim();
            try {
                process(input);
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }
}