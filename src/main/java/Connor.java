import java.util.Scanner;
import java.util.ArrayList;

public class Connor {
    private static final String CURRENT_VERSION = "Version 1.4";
    private static final String LINE = "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━";
    private static final String INDENT = "    ";

    private static boolean isActive = true;

    private static ArrayList<Task> taskList = new ArrayList<>();

    private static void interpret(String s) {
        // Split between command and description
        String[] statement = s.split(" ",2);
        // Standardise command format
        String x = statement[0].toLowerCase();
        switch (x) {
        case "exit":
        case "bye": {
            isActive = false;
            print("Farewell. See you next time!");
            break;
        }
        case "list": {
            if (taskList.size() == 0) {
                print("Your task list is empty.");
                return;
            }
            print("Here are your current tasks: ");
            for (int i = 1; i <= taskList.size(); i++) {
                print(INDENT + Integer.toString(i) + ". " + taskList.get(i - 1));
            }
            break;
        }
        case "todo":
        case "deadline":
        case "event": {
            addTask(x, statement[1]);
            break;
        }
        case "mark": {
            int taskNo = Integer.parseInt(statement[1]) - 1;
            Task t = taskList.get(taskNo);
            t.mark();
            print("Good job! I have marked the following task as completed: ");
            print(INDENT + t);
            break;
        }
        case "unmark": {
            int taskNo = Integer.parseInt(statement[1]) - 1;
            Task t = taskList.get(taskNo);
            t.unmark();
            print("Understood. I have unmarked the following task: ");
            print(INDENT + t);
            break;
        }
        default: {
            print("My apologies, I do not understand this.");
            return;
        }
        }
    }

    private static void addTask(String taskType, String desc) {
        if (taskType.equals("todo")) {
            ToDo todo = new ToDo(desc);
            taskList.add(todo);
            print("Alright, I have added a new task: ");
            print(INDENT + todo.toString());
        } else if (taskType.equals("deadline")) {
            if (!desc.contains("/by")) {
                print("Error! Wrong format for deadlines.");
                print("Deadline tasks must include \"/by\" in the description.");
                print("Example: ");
                print(">>> deadline Finish Project /by Monday Morning");
                return;
            }
            String[] phrase = desc.split("/by",2);
            Deadline deadline = new Deadline(phrase[0].trim(), phrase[1].trim());
            taskList.add(deadline);
            print("Alright, I have added a new task: ");
            print(INDENT + deadline.toString());
        } else if (taskType.equals("event")) {
            if (!desc.contains("/at")) {
                print("Error! Wrong format for events.");
                print("Event tasks must include \"/at\" in the description.");
                print("Example: ");
                print(">>> event Wedding Party /at May 5th");
                return;
            }
            String[] phrase = desc.split("/at", 2);
            Event event = new Event(phrase[0].trim(), phrase[1].trim());
            taskList.add(event);
            print("Alright, I have added a new task: ");
            print(INDENT + event.toString());
        } else {
            // Something has gone wrong
            print("Oh no! Incorrect Task type!");
            return;
        }
        // After task is added show current no. of tasks
        print("");
        print("You currently have " + taskList.size() + " tasks.");
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
