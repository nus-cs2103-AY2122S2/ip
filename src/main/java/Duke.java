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
                int mark_idx = Integer.parseInt(action.getArgs()[0]) - 1;
                tasks.mark(mark_idx);
                say("[*] Marked the following task as done:\n" +
                    "\t" + tasks.getTask(mark_idx).nameWithStatus());
                break;

            case "unmark":
                // User input is 1-indexed, list uses 0-index
                int unmark_idx = Integer.parseInt(action.getArgs()[0]) - 1;
                tasks.unmark(unmark_idx);
                say("[*] Marked the following task as not done:\n" +
                    "\t" + tasks.getTask(unmark_idx).nameWithStatus());
                break;

            default:
                tasks.addTask(new Task(action.getName()));
                say("[+] Added: " + action.getName());

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
