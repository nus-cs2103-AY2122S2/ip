import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String INPUT_BYE = "bye";
    private static final String INPUT_LIST = "list";
    private static final String INPUT_MARK = "mark";
    private static final String INPUT_UNMARK = "unmark";

    // set task at index 0 to be a dummy filler value
    private static final ArrayList<Task> tasks = new ArrayList<>(List.of(new Task("")));
    private static int count = 1;
    private static boolean processNext = true;

    public static void main(String[] args) {
        welcome();

        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");

        while (processNext) {
            switch (input[0]) {
                case INPUT_BYE:
                    processNext = false;
                    break;
                case INPUT_LIST:
                    printTasks();
                    break;
                case INPUT_MARK:
                    int num = Integer.parseInt(input[1]);
                    Task task = tasks.get(num);
                    task.markAsDone();
                    echo("I've marked the following task as completed:\n" + task.toString());
                    break;
                case INPUT_UNMARK:
                    num = Integer.parseInt(input[1]);
                    task = tasks.get(num);
                    task.markAsUndone();
                    echo("Understood. I've marked the following task as incomplete:\n" + task.toString());
                    break;
                default:
                    String description = String.join(" ", input);
                    tasks.add(new Task(description));
                    count++;
                    echo("Added: " + description);
                    break;
            }
            input = scanner.nextLine().split(" ");
        }
        System.out.println("Goodbye. J.A.R.V.I.S. systems powering off...");
    }

    public static void welcome() {
        String logo =
                "      _          _          ____       __     __      ___       ____      \n"
                        + "     | |        / \\        |  _ \\      \\ \\   / /     |_ _|     / ___|     \n"
                        + "  _  | |       / _ \\       | |_) |      \\ \\ / /       | |      \\___ \\     \n"
                        + " | |_| |  _   / ___ \\   _  |  _ <   _    \\ V /    _   | |   _   ___) |  _ \n"
                        + "  \\___/  (_) /_/   \\_\\ (_) |_| \\_\\ (_)    \\_/    (_) |___| (_) |____/  (_)\n"
                        + "                                                                          \n";
        System.out.println("Starting up...\n"
                + "Online and ready.\n"
                + logo
                + "At your service.\n");
    }

    public static void echo(String str) {
        System.out.println("------------------------------");
        System.out.println(str);
        System.out.println("------------------------------");
    }

    private static void printTasks() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < count; i++) {
            sb.append(i).append(". ").append(tasks.get(i).toString());
            if (i + 1 != count) {
                sb.append("\n");
            }
        }
        echo(sb.toString());
    }
}
