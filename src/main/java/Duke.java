import java.util.Scanner;

class Color {
    public static final String NONE = "\033[m";

    public static final String GREEN = "\033[92m";
    public static final String RED = "\033[31m";
    public static final String PURPLE = "\033[35m";
}

public class Duke {
    private static void printBanner() {
        String logo = "███╗░░██╗██╗██╗░░██╗██╗░░██╗██╗　　　　 　∧_∧\n"
                    + "████╗░██║██║██║░██╔╝██║░██╔╝██║　　　　 ( ﾟωﾟ)\n"
                    + "██╔██╗██║██║█████═╝░█████═╝░██║　　　　 /ｕ ｕ\n"
                    + "██║╚████║██║██╔═██╗░██╔═██╗░██║　　　　/ / /\n"
                    + "██║░╚███║██║██║░╚██╗██║░╚██╗██║　　　 (ノノ\n"
                    + "╚═╝░░╚══╝╚═╝╚═╝░░╚═╝╚═╝░░╚═╝╚═╝　　　彡\n";

        String description = "Your Personal Assistant Chatbot that helps you keep track of the important things in life";

        System.out.println(Color.PURPLE + logo + Color.NONE);
        System.out.println(description);
    }

    private static TaskList tasks = new TaskList();
    private static final CommandParser cmd = new CommandParser(new Scanner(System.in));

    /**
     * Method for chatbot to print message in a formatted style.
     *
     * @param message Message to print
     */
    private static void say(String message) {
        // Set color theme for Nikki's text
        String defaultColor = Color.GREEN;
        System.out.println(defaultColor);

        System.out.println("<<<<<<<<");
        System.out.println(message);
        // Default back to green to allow different colored messages
        System.out.println(defaultColor + ">>>>>>>>");

        // Reset to default
        System.out.println(Color.NONE);
    }

    /**
     * Method for chatbot to print error messages.
     *
     * @param message Error message to print
     */
    private static void error(String message) {
        say(Color.RED + message);
    }

    /**
     * Log the addition of tasks in the same format
     *
     * @param task Task added to be logged
     */
    private static void logNewTask(Task task) {
        say(String.format(
                "[+] Added following task:\n" +
                "\t%s\n" +
                "Now you have %d tasks in the list.",
                task.nameWithStatus(), tasks.size()));
    }

    /**
     * Perform certain behaviours according to the command passed.
     *
     * @param action Command from user
     * @throws DukeException General exception for invalid user command: invalid command, invalid arguments, etc.
     */
    private static void handleAction(Command action) throws DukeException {
        switch (action.getName()) {
        case "bye":
            say("Bye! See you later!");
            System.exit(0);
            break;

        case "list":
            say("[*] Here are the tasks in your list:\n" +
                tasks.toString());
            break;

        case "mark":
            // User input is 1-indexed, list uses 0-index
            int markIdx = Integer.parseInt(action.getArgs()) - 1;
            tasks.mark(markIdx);
            say("[*] Marked the following task as done:\n" +
                "\t" + tasks.getTask(markIdx).nameWithStatus());
            break;

        case "unmark":
            // User input is 1-indexed, list uses 0-index
            int unmarkIdx = Integer.parseInt(action.getArgs()) - 1;
            tasks.unmark(unmarkIdx);
            say("[*] Marked the following task as not done:\n" +
                "\t" + tasks.getTask(unmarkIdx).nameWithStatus());
            break;

        case "delete":
            // User input is 1-indexed, list uses 0-index
            int dltIdx = Integer.parseInt(action.getArgs()) - 1;
            Task deletedTask = tasks.removeTask(dltIdx);
            say(String.format(
                    "[-] Removed this task:\n" +
                            "\t%s\n" +
                            "Now you have %d tasks in the list.",
                    deletedTask.nameWithStatus(), tasks.size()));
            break;

        case "todo":
            String todoName = action.getArgs();
            Todo todo = new Todo(todoName);
            tasks.addTask(todo);
            logNewTask(todo);
            break;

        case "deadline":
            String deadlineName = action.getArgs();
            String deadlineDate = action.getKwargs().get("by");
            Deadline deadline = new Deadline(deadlineName, deadlineDate);
            tasks.addTask(deadline);
            logNewTask(deadline);
            break;

        case "event":
            String eventName = action.getArgs();
            String eventDate = action.getKwargs().get("at");
            Event event = new Event(eventName, eventDate);
            tasks.addTask(event);
            logNewTask(event);
            break;

        default:
            throw new DukeException("I don't know what to do");

        }
    }

    public static void main(String[] args) {
        printBanner();

        String introduction = "Hello, I'm Nikki\n" +
                              "What can I do for you?";
        say(introduction);

        while (true) {
            try {
                Command action = cmd.readAndParse();
                handleAction(action);
            } catch (DukeException e) {
                error("!( ｀Д´)ﾉ  " + e.getLocalizedMessage());
            }
        }
    }
}
