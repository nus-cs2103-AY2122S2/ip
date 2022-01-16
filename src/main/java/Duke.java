import task.*;
import java.util.Scanner;

public class Duke {
    private static final String INPUT_BYE = "bye";
    private static final String INPUT_LIST = "list";
    private static final String INPUT_MARK = "mark";
    private static final String INPUT_UNMARK = "unmark";

    private static final String TASK_TODO = "todo";
    private static final String TASK_DEADLINE = "deadline";
    private static final String TASK_EVENT = "event";
    private static final String TASK_BY = "/by";
    private static final String TASK_AT = "/at";

    private static final Task[] tasks = new Task[100];
    private static int count = 1;
    private static boolean processNext = true;

    public static void main(String[] args) {
        welcome();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (processNext) {
            String[] tokens = input.split(" ");
            switch (tokens[0]) {
                case INPUT_BYE:
                    processNext = false;
                    echo("Goodbye. J.A.R.V.I.S. systems powering off...");
                    return;
                case INPUT_LIST:
                    printTasks();
                    break;
                case INPUT_MARK:
                    markAsDone(tokens);
                    break;
                case INPUT_UNMARK:
                    markAsUndone(tokens);
                    break;
                case TASK_TODO:
                    addTodo(input);
                    break;
                case TASK_DEADLINE:
                    addDeadline(input);
                    break;
                case TASK_EVENT:
                    addEvent(input);
                    break;
                default:
                    // TODO: to be implemented in future levels
                    break;
            }
            input = scanner.nextLine();
        }
        scanner.close();
    }

    private static void welcome() {
        String logo =
                "      _          _          ____       __     __      ___       ____\n"
                        + "     | |        / \\        |  _ \\      \\ \\   / /     |_ _|     / ___|\n"
                        + "  _  | |       / _ \\       | |_) |      \\ \\ / /       | |      \\___ \\\n"
                        + " | |_| |  _   / ___ \\   _  |  _ <   _    \\ V /    _   | |   _   ___) |  _\n"
                        + "  \\___/  (_) /_/   \\_\\ (_) |_| \\_\\ (_)    \\_/    (_) |___| (_) |____/  (_)\n\n";

        System.out.println("Starting up...\n"
                + "Online and ready.\n"
                + logo
                + "At your service.\n");
    }

    private static void echo(String str) {
        System.out.println("------------------------------------------------------------");
        System.out.println(str);
        System.out.println("------------------------------------------------------------");
    }

    private static void printTasks() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < count; i++) {
            sb.append(i).append(".\t").append(tasks[i]);
            if (i + 1 != count) {
                sb.append("\n");
            }
        }
        echo(sb.toString());
    }

    private static void markAsDone(String[] tokens) {
        int num = Integer.parseInt(tokens[1]);
        Task task = tasks[num];
        task.markAsDone();
        echo("I've marked the following task as completed:\n\t" + task);
    }

    private static void markAsUndone(String[] tokens) {
        int num = Integer.parseInt(tokens[1]);
        Task task = tasks[num];
        task.markAsUndone();
        echo("Understood. I've marked the following task as incomplete:\n\t" + task);
    }

    private static void addTodo(String input) {
        String description = input.substring(TASK_TODO.length() + 1);
        tasks[count] = new Todo(description);
        echo("Got it. I've added this task:\n\t"
                + tasks[count] + "\n"
                + "Now you have " + count + " task(s) in your list.");
        count++;
    }

    private static void addDeadline(String input) {
        String[] split = input.split(TASK_BY);
        String description = split[0].substring(TASK_DEADLINE.length() + 1);
        tasks[count] = new Deadline(description, split[1]);
        echo("Got it. I've added this deadline:\n\t"
                + tasks[count] + "\n"
                + "Now you have " + count + " task(s) in your list.");
        count++;
    }

    public static void addEvent(String input) {
        String[] split = input.split(TASK_AT);
        String description = split[0].substring(TASK_EVENT.length() + 1);
        tasks[count] = new Event(description, split[1]);
        echo("Got it. I've added this event:\n\t"
                + tasks[count] + "\n"
                + "Now you have " + count + " task(s) in your list.");
        count++;
    }
}
