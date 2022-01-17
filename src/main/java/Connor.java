import java.util.Scanner;
import java.util.ArrayList;

public class Connor {
    private static final String CURRENT_VERSION = "Version 1.6";
    private static final String LINE = "┄".repeat(66);
    private static final String INDENT = " ".repeat(4);

    private static boolean isActive = true;

    private static ArrayList<Task> taskList = new ArrayList<>();

    private static void interpret(String s) {
        // Split between command and description
        String[] statement = s.trim().split(" ",2);
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
            viewTasks();
            break;
        }
        case "todo":
        case "deadline":
        case "event": {
            try {
                String desc = statement[1].trim();
                if (desc.isBlank()) {
                    print("Error! Tasks cannot have an empty description.");
                    return;
                }
                addTask(x, desc);
            } catch (ArrayIndexOutOfBoundsException e) {
                print("Error! Tasks cannot have an empty description.");
            }
            break;
        }
        case "delete": {
            try {
                int taskNo = Integer.parseInt(statement[1]) - 1;
                deleteTask(taskNo);
                print("");
                viewTasks();
            } catch (NumberFormatException e) {
                print("Error! Index must be a valid integer.");
            } catch (ArrayIndexOutOfBoundsException e) {
                print("Error! Index cannot be empty.");
            }
            break;
        }
        case "mark":
        case "unmark": {
            try {
                int taskNo = Integer.parseInt(statement[1]) - 1;
                markStatus(x, taskNo);
            } catch (NumberFormatException e) {
                print("Error! Index must be a valid integer.");
            } catch (ArrayIndexOutOfBoundsException e) {
                print("Error! Index cannot be empty.");
            }
            break;
        }
        default: {
            print("My apologies, I don't understand what '" + statement[0] + "' means.");
        }
        }
    }

    private static void viewTasks() {
        if (taskList.size() == 0) {
            print("Your task list is empty.");
            return;
        }
        print("Here are your current tasks: ");
        for (int i = 1; i <= taskList.size(); i++) {
            print(INDENT + Integer.toString(i) + ". " + taskList.get(i - 1));
        }
    }

    private static void addTask(String taskType, String desc) {
        switch (taskType) {
        case "todo":
            ToDo todo = new ToDo(desc);
            taskList.add(todo);
            print("Alright, I've added a new task: ");
            print(INDENT + todo.toString());
            break;
        case "deadline": {
            if (!desc.contains("/by")) {
                print("Error! Wrong format for deadlines.");
                print("");
                print("Deadline tasks must include \"/by\" in the description.");
                print("Example: ");
                print(">>> deadline Finish Project /by Monday Morning");
                return;
            }
            String[] phrase = desc.split("/by", 2);
            String thing = phrase[0].trim();
            String when  = phrase[1].trim();
            if (thing.isBlank() || when.isBlank()) {
                print("Error! Deadlines cannot have empty descriptions or dates.");
                return;
            }
            Deadline deadline = new Deadline(thing, when);
            taskList.add(deadline);
            print("Alright, I've added a new task: ");
            print(INDENT + deadline.toString());
            break;
        }
        case "event": {
            if (!desc.contains("/at")) {
                print("Error! Wrong format for events.");
                print("");
                print("Event tasks must include \"/at\" in the description.");
                print("Example: ");
                print(">>> event Wedding Party /at May 5th");
                return;
            }
            String[] phrase = desc.split("/at", 2);
            String thing = phrase[0].trim();
            String when  = phrase[1].trim();
            if (thing.isBlank() || when.isBlank()) {
                print("Error! Events cannot have empty descriptions or dates.");
                return;
            }
            Event event = new Event(thing, when);
            taskList.add(event);
            print("Alright, I've added a new task: ");
            print(INDENT + event.toString());
            break;
        }
        default:
            // Something has gone wrong
            print("Oh no! Incorrect Task type!");
            return;
        }
        // After task is added show current no. of tasks
        print("");
        getNoOfTasks();
    }

    private static void deleteTask(int index) {
        try {
            Task t = taskList.get(index);
            taskList.remove(index);
            print("Alright, I've deleted the task: ");
            print(INDENT + t);
        } catch (IndexOutOfBoundsException e) {
            print("Error! Given index is out of range.");
        }
    }

    private static void markStatus(String status, int index) {
        if (status.equals("mark")) {
            try {
                Task t = taskList.get(index);
                t.mark();
                print("Good job! I've marked the following task as completed: ");
                print(INDENT + t);
            } catch (IndexOutOfBoundsException e) {
                print("Error! Given index is out of range.");
                getNoOfTasks();
            }
        } else {
            // Unmark
            try {
                Task t = taskList.get(index);
                t.unmark();
                print("Understood. I've unmarked the following task: ");
                print(INDENT + t);
            } catch (IndexOutOfBoundsException e) {
                if (taskList.isEmpty()) {
                    print("Error! I can't mark an empty task list!");
                    return;
                }
                print("Error! Given index is out of range.");
                getNoOfTasks();
            }
        }
    }

    private static void getNoOfTasks() {
        String plurality = taskList.size() == 1 ? "" : "s";
        print("You have " + taskList.size() + " task" + plurality + ".");
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
            print(input);
            print(LINE);
            interpret(input);
            print(LINE);
        }
        sc.close();
    }

}
