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

    private static void execute(String command) throws DukeException, 
            NumberFormatException, ArrayIndexOutOfBoundsException {
        String[] commandTokens = command.split(" ", 2);
        switch (commandTokens[0]) {
        case "list":
            listTask();
            break;
        case "mark":
            int indexOfTaskToMark = Integer.parseInt(commandTokens[1]);
            if (!(1 <= indexOfTaskToMark && indexOfTaskToMark <= numberOfTasks)) {
                throw new DukeException("The index of task to be marked is invalid!!");
            }
            mark(indexOfTaskToMark);
            break;
        case "unmark":
            int indexOfTaskToUnmark = Integer.parseInt(commandTokens[1]);
            if (!(1 <= indexOfTaskToUnmark && indexOfTaskToUnmark <= numberOfTasks)) {
                throw new DukeException("The index of task to be unmarked is invalid!!");
            }
            unmark(indexOfTaskToUnmark);
            break;
        case "todo":
            addTask("todo", commandTokens[1], "");
            break;
        case "deadline":
            String[] deadlineTokens = commandTokens[1].split(" /by ");
            addTask("deadline", deadlineTokens[0], deadlineTokens[1]);
            break;
        case "event":
            String[] eventTokens = commandTokens[1].split(" /at ");
            addTask("event", eventTokens[0], eventTokens[1]);
            break;
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :(");
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
                try {
                    execute(command);
                } catch (DukeException exception) {
                    System.out.println("Uh oh! " + exception.getMessage());
                } catch (NumberFormatException exception) {
                    System.out.println("Please use index of task to indicate which task to mark/unmark");
                } catch (ArrayIndexOutOfBoundsException exception) {
                    System.out.println("Seems like the command is incomplete -_-");
                }
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