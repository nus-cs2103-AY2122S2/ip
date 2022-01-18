import java.util.*;

public class Duke {

    public static String indent = "    ";
    public static String separator = "--------------------------------------------";
    public static String[] openingMessage = new String[] {"Hello! I'm Duke", "What can I do for you?"};
    public static String closingMessage = "Bye. Hope to see you again soon!";

    public static ArrayList<String> allTasks = new ArrayList<>();

    public static void printIndent(String s) {
        System.out.println(indent + s);
    }

    public static void prettyPrint(String s) {
        printIndent(separator);
        printIndent(s);
        printIndent(separator + "\n");
    }

    public static void prettyPrint(String[] messages) {
        printIndent(separator);
        for (String message : messages) printIndent(message);
        printIndent(separator + "\n");
    }

    public static void displayTasks() {
        printIndent(separator);
        for (int i = 0; i < allTasks.size(); i++) {
            printIndent(String.format("%d. %s", i+1, allTasks.get(i)));
        }
        printIndent(separator + "\n");
    }

    public static void addTask(String task) {
        allTasks.add(task);
        prettyPrint(String.format("added: %s", task));
    }

    public static void main(String[] args) {
        String logo = indent + " ____        _        \n"
                + indent + "|  _ \\ _   _| | _____ \n"
                + indent + "| | | | | | | |/ / _ \\\n"
                + indent + "| |_| | |_| |   <  __/\n"
                + indent + "|____/ \\__,_|_|\\_\\___|\n";

        // introduction messages
        System.out.println(logo);
        prettyPrint(openingMessage);

        // read input
        Scanner sc = new Scanner(System.in);
        String userInput;
        boolean finished = false;
        while (!finished) {
            userInput = sc.nextLine();

            switch (userInput) {
                case "bye":
                    prettyPrint(closingMessage);
                    finished = true;
                    break;
                case "list":
                    displayTasks();
                    break;
                default:
                    addTask(userInput);
            }
        }
    }
}
