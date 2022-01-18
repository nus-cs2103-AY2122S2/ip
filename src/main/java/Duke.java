import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String delimiter = "*******************************************************";
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static int numberOfTasks = 0;
    
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
        tasks.add(newTask);
        numberOfTasks++;
        System.out.println("Got it. I've added this task: ");
        System.out.println(newTask);
        System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
    }

    private static void listTask() {
        if (numberOfTasks == 0) {
            System.out.println("Congratulations! There are no tasks in your list :)");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < numberOfTasks; i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    private static void markTask(int taskNumber) {
        tasks.get(taskNumber - 1).mark();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(tasks.get(taskNumber - 1));
    }

    private static void unmarkTask(int taskNumber) {
        tasks.get(taskNumber - 1).unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(taskNumber - 1));
    }

    private static void deleteTask(int taskNumber) {
        Task taskToBeRemoved = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);
        numberOfTasks--;
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskToBeRemoved);
        System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
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
            markTask(indexOfTaskToMark);
            break;
        case "unmark":
            int indexOfTaskToUnmark = Integer.parseInt(commandTokens[1]);
            if (!(1 <= indexOfTaskToUnmark && indexOfTaskToUnmark <= numberOfTasks)) {
                throw new DukeException("The index of task to be unmarked is invalid!!");
            }
            unmarkTask(indexOfTaskToUnmark);
            break;
        case "delete":
            int indexOfTaskToDelete = Integer.parseInt(commandTokens[1]);
            if (!(1 <= indexOfTaskToDelete && indexOfTaskToDelete <= numberOfTasks)) {
                throw new DukeException("The index of task to be deleted is invalid!!");
            }
            deleteTask(indexOfTaskToDelete);
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
        greet();
        run();
        exit();
    }
}