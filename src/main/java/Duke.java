import java.util.Scanner;

public class Duke {
    private static final int MAX_TASK = 100;
    private static Task[] tasks;
    private static int numberOfTasks;
    private static final String delimiter = "*******************************************************";

    private static void init() {
        tasks = new Task[MAX_TASK];
        numberOfTasks = 0;
    }

    private static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void addTask(String task) {
        tasks[numberOfTasks] = new Task(task);
        numberOfTasks++;
        System.out.println("added: " + task);
    }

    private static void listTask() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < numberOfTasks; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
    }

    private static void mark(int taskNumber) {
        tasks[taskNumber - 1].mark();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(tasks[taskNumber - 1]);
    }

    private static void unmark(int taskNumber) {
        tasks[taskNumber - 1].unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks[taskNumber - 1]);
    }

    private static void execute(String command) {
        String[] commandTokens = command.split(" ");
        switch (commandTokens[0]) {
        case "list":
            listTask();
            break;
        case "mark":
            mark(Integer.parseInt(commandTokens[1]));
            break;
        case "unmark":
            unmark(Integer.parseInt(commandTokens[1]));
            break;
        default:
            addTask(command);
        }
    }

    private static void run() {
        Scanner scanner = new Scanner(System.in);
        boolean isQuitting = false;
        while (!isQuitting) {
            System.out.println(delimiter);
            System.out.print("Enter your command: ");
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                isQuitting = true;
            } else {
                execute(command);
            }
        }
    }

    public static void main(String[] args) {
        init();
        greet();
        run();
        exit();
    }
}