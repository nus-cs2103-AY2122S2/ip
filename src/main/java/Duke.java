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
    private static ArrayList<String> taskList = new ArrayList<>();

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

    public static void main(String[] args) {
        greet();
        Scanner s = new Scanner(System.in);
        boolean end = false;
        while (!end) {
            String input = s.nextLine().toLowerCase();
            if (input.equals("bye")) {
                end = true;
                exit();
            }
            else if (input.equals("list")) {
                StringBuilder listString = new StringBuilder();
                for (int i = 0; i < taskList.size(); i++) {
                    listString.append(i + 1).append(". ").append(taskList.get(i)).append("\n");
                }
                System.out.println(BORDER + listString + BORDER);
            } else {
                taskList.add(input);
                System.out.println(BORDER + "added: " + input + "\n" + BORDER);
            }
        }
    }
}