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

    private static void addTask(String taskType, String taskName, String dateTime) {
        Task newTask;
        if (taskType.equals("todo")) {
            newTask = new ToDo(taskName);
        } else if (taskType.equals("deadline")) {
            newTask = new Deadline(taskName, dateTime);
        } else {
            newTask = new Event(taskName, dateTime);
        }
        tasks[numberOfTasks] = newTask;
        numberOfTasks++;
        System.out.println("Got it. I've added this task: ");
        System.out.println(newTask);
        System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
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
        case "todo":
            //fallthrough
        case "deadline":
            //fallthrough
        case "event":
            String taskName = "";
            String dateTime = "";
            boolean isTaskNameTurn = true;
            for (int i = 1; i < commandTokens.length; i++) {
                String currentToken = commandTokens[i];
                if (currentToken.equals("/by") || currentToken.equals("/at")) {
                    isTaskNameTurn = false;
                    continue;
                }
                if (isTaskNameTurn) {
                    taskName += (taskName.equals("") ? "" : " ") + currentToken;
                } else {
                    dateTime += (dateTime.equals("") ? "" : " ") + currentToken;
                }
            }
            addTask(commandTokens[0], taskName, dateTime);
        default:
            ;
        }
    }

    private static void run() {
        Scanner scanner = new Scanner(System.in);
        boolean isQuitting = false;
        while (!isQuitting) {
            System.out.println(delimiter);
            System.out.print("Enter your command: ");
            String command = scanner.nextLine();
            System.out.println();
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