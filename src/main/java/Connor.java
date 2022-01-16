import java.util.Scanner;
import java.util.ArrayList;

public class Connor {
    private static final String CURRENT_VERSION = "Version 1.3";
    private static final String LINE = "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━";
    private static final String INDENT = "    ";

    private static boolean isActive = true;

    private static ArrayList<Task> xs = new ArrayList<>();

    private static void interpret(String s) {
        // Standardise Format
        String[] statement = s.split(" ",2);
        switch (statement[0].toLowerCase()) {
        case "bye": {
            isActive = false;
            print("Farewell. See you next time!");
            break;
        }
        case "list": {
            if (xs.size() == 0) {
                print("List is empty!");
            } else {
                print("Here are your current tasks: ");
                for (int i = 1; i <= xs.size(); i++) {
                    print(INDENT + Integer.toString(i) + ". " + xs.get(i - 1));
                }
            }
            break;
        }
        case "mark": {
            int taskNo = Integer.parseInt(statement[1]) - 1;
            Task t = xs.get(taskNo);
            t.mark();
            print("Good job! I have marked the following task as completed: ");
            print(INDENT + t);
            break;
        }
        case "unmark": {
            int taskNo = Integer.parseInt(statement[1]) - 1;
            Task t = xs.get(taskNo);
            t.unmark();
            print("Understood. I have unmarked the following task: ");
            print(INDENT + t);
            break;
        }
        default: {
            Task task = new Task(s);
            xs.add(task);
            print("Alright, I have added a new task: ");
            print(INDENT + task.toString());
            print("");
            print("You currently have " + xs.size() + " tasks.");
        }
        }
    }

    private static void print(String s) {
        System.out.println(s);
    }

    public static void main(String[] args) {
        String logo = " .d8888b.\n"
                + "d88P  Y88b\n"
                + "888    888\n"
                + "888         .d88b.  88888b.  88888b.   .d88b.  888d888\n"
                + "888        d88\"\"88b 888 \"88b 888 \"88b d88\"\"88b 888P\"\n"
                + "888    888 888  888 888  888 888  888 888  888 888\n"
                + "Y88b  d88P Y88..88P 888  888 888  888 Y88..88P 88\n"
                + " \"Y8888P\"   \"Y88P\"  888  888 888  888  \"Y88P\"  888\n";
        print("\n" + logo + "\n" + CURRENT_VERSION + "\n");
        print(LINE);
        print("Hi, my name is Connor! I'm your personalised android assistant.\n"
                + "How may I help?");
        print(LINE);
        // Greeting ends, User can input now
        Scanner sc = new Scanner(System.in);
        while (isActive) {
            System.out.print(">>> ");
            String input = sc.nextLine();
            print(LINE);
            interpret(input);
            print(LINE);
        }
        sc.close();
    }

}
