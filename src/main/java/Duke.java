import java.util.Scanner;

class Color {
    public static final String none = "\033[m";

    // Foreground colors
    public static final String green  = "\033[92m";
    public static final String purple = "\033[35m";

    // Background colors
    public static final String bgBlack = "\033[40m";
    public static final String bgWhite = "\033[107m";
}

public class Duke {
    private static void printBanner() {
        String logo = "███╗░░██╗██╗██╗░░██╗██╗░░██╗██╗\n" +
                      "████╗░██║██║██║░██╔╝██║░██╔╝██║\n" +
                      "██╔██╗██║██║█████═╝░█████═╝░██║\n" +
                      "██║╚████║██║██╔═██╗░██╔═██╗░██║\n" +
                      "██║░╚███║██║██║░╚██╗██║░╚██╗██║\n" +
                      "╚═╝░░╚══╝╚═╝╚═╝░░╚═╝╚═╝░░╚═╝╚═╝";

        String description = "Your Personal Assistant Chatbot that helps you keep track of the important things in life";

        System.out.println(Color.purple + logo + Color.none);
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
        System.out.println(Color.green);

        System.out.println("<<<<<<<<");
        System.out.println(message);
        System.out.println(">>>>>>>>");

        // Reset to default
        System.out.println(Color.none);
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

    private static void handleAction(Command action) {
        switch (action.getName()) {
            case "bye":
                say("Bye! See you later!");
                System.exit(0);

            case "list":
                say("Here are the tasks in your list:\n" +
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
                Task task = new Task(action.getName());
                tasks.addTask(task);
                logNewTask(task);

        }
    }

    public static void main(String[] args) {
        printBanner();

        String introduction = "Hello, I'm Nikki\n" +
                              "What can I do for you?";
        say(introduction);

        while (true) {
            Command action = cmd.readAndParse();
            handleAction(action);
        }
    }
}
