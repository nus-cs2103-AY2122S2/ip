import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String LOGO =
            "   ___      _  ______       _   \n" +
                    "  |_  |    | | | ___ \\     | |  \n" +
                    "    | | ___| |_| |_/ / ___ | |_ \n" +
                    "    | |/ _ \\ __| ___ \\/ _ \\| __|\n" +
                    "/\\__/ /  __/ |_| |_/ / (_) | |_ \n" +
                    "\\____/ \\___|\\__\\____/ \\___/ \\__|\n";
    private static final String BORDER = "________________________________\n";
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static boolean end = false;

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
     * Returns a string of the specified task with its description and status icon.
     */
    private static String printStatusWithDesc(Task t) {
        return "[" + t.getStatusIcon() + "] " + t.getDescription() + "\n";
    }

    /**
     * Marks the specified task as done.
     */
    private static void mark(int tasknum) {
        Task t = taskList.get(tasknum - 1);
        System.out.println(BORDER + "Well done! I've marked this task as done: \n");
        t.markAsDone();
        System.out.println(printStatusWithDesc(t) + BORDER);
    }

    /**
     * Unmarks the specified task.
     */
    private static void unmark(int tasknum) {
        Task t = taskList.get(tasknum - 1);
        System.out.println(BORDER + "No problem, I've marked this task as undone: \n");
        t.unmarkAsDone();
        System.out.println(printStatusWithDesc(t) + BORDER);
    }

    /**
     * Processes the input.
     */
    private static void process(String input) {
        String[] arr = input.split(" ");
        String command = arr[0]; // first word of the user input
        if (command.equals("bye")) {
            end = true;
            exit();
        } else if (command.equals("list")) {
            StringBuilder listString = new StringBuilder();
            for (int i = 0; i < taskList.size(); i++) {
                Task t = taskList.get(i);
                listString.append(i + 1).append(".[").append(t.getStatusIcon()).append("] ").append(t.getDescription()).
                        append("\n");
            }
            System.out.println(BORDER + listString + BORDER);
        } else if (command.equals("mark")) {
            int num = Integer.parseInt(arr[1]);
            mark(num);
        } else if (command.equals("unmark")) {
            int num = Integer.parseInt(arr[1]);
            unmark(num);
        } else {
            taskList.add(new Task(input));
            System.out.println(BORDER + "added: " + input + "\n" + BORDER);
        }
    }

    public static void main(String[] args) {
        greet();
        Scanner s = new Scanner(System.in);
        while (!end) {
            String input = s.nextLine().toLowerCase();
            process(input);
        }
    }
}