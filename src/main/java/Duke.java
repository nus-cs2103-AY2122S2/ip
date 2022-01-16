import java.util.Scanner;

public class Duke {
    private static final int MAX_TASK = 100;
    private static Task[] tasks;
    private static int number_of_task;
    private static final String delimiter = "*******************************************************";

    private static void init() {
        tasks = new Task[MAX_TASK];
        number_of_task = 0;
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

    private static void echo(String command) {
        System.out.println(command);
    }

    private static void addTask(String task) {
        tasks[number_of_task] = new Task(task);
        number_of_task++;
    }

    private static void listTask() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < number_of_task; i++) {
            System.out.printf("%d. %s\n", i + 1, tasks[i]);
        }
    }

    private static void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(delimiter);
            System.out.print("Enter your command: ");
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                listTask();
            } else {
                addTask(command);
                echo("added: " + command);
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