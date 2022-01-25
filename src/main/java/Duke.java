import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String HORIZONTAL_LINE = "\t" + "____________________________________________________________";

    private static ArrayList<Task> savedTasks = new ArrayList<>();

    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DELETE = "delete";

    private static final String PREFIX_DEADLINE = "/by";
    private static final String PREFIX_EVENT = "/at";

    public static void main(String[] args) {
        printGreeting();
        try {
            savedTasks = Storage.readDataFromFile();
        } catch (IOException e) {
            System.out.println("File not found.");
        }
        Scanner sc = new Scanner(System.in);

        while (true) {
            String userInput = sc.nextLine();
            try {
                executeCommand(userInput);
            } catch (UnknownCommandException e) {
                printUnknownCommandException();
            } catch (EmptyTaskException e) {
                printEmptyTaskException();
            }
        }
    }

    private static void printEmptyTaskException() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t" + "☹ OOPS!!! Missing arguments.");
        System.out.println(HORIZONTAL_LINE);
    }
    private static void printUnknownCommandException() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t" + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println(HORIZONTAL_LINE);
    }

    private static void executeCommand(String userInput) throws UnknownCommandException, EmptyTaskException {
        String[] command = parseUserInput(userInput);
        String commandType = command[0];
        String commandArgument = command[1];

        if (commandArgument.equals("") && !commandType.equals(COMMAND_LIST) && !commandType.equals(COMMAND_BYE)) {
            throw new EmptyTaskException();
        }

        switch (commandType) {
        case COMMAND_BYE:
            try {
                executeExit();
            } catch (IOException e) {
                System.out.println("File not found");
            }
            break;
        case COMMAND_LIST:
            executeList();
            break;
        case COMMAND_MARK:
            try {
                executeMark(commandArgument);
            } catch (InvalidIndexException e) {
                printInvalidIndexException();
            }
            break;
        case COMMAND_UNMARK:
            try {
                executeUnmark(commandArgument);
            } catch (InvalidIndexException e) {
                printInvalidIndexException();
            }
            break;
        case COMMAND_DELETE:
            try {
                executeDelete(commandArgument);
            } catch (InvalidIndexException e) {
                printInvalidIndexException();
            }
            break;
        case COMMAND_TODO:
            executeAddTodo(commandArgument);
            break;
        case COMMAND_DEADLINE:
            try {
                executeAddDeadline(commandArgument);
            } catch (MissingDateTimeException e) {
                printMissingDateTimeException();
            }
            break;
        case COMMAND_EVENT:
            try {
                executeAddEvent(commandArgument);
            } catch (MissingDateTimeException e) {
                printMissingDateTimeException();
            }
            break;
        default:
            throw new UnknownCommandException();
        }
    }

    private static void printInvalidIndexException() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t" + "☹ OOPS!!! Invalid index.");
        System.out.println(HORIZONTAL_LINE);
    }
    private static void printMissingDateTimeException() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t" + "☹ OOPS!!! Missing date/time. Please specify it.");
        System.out.println(HORIZONTAL_LINE);
    }
    private static void executeAddTodo(String commandArgument) {
        savedTasks.add(new Todo(commandArgument));
        printConfirmAdd();
    }

    private static void executeAddDeadline(String commandArgument) throws MissingDateTimeException {
        String[] deadlineDetail = commandArgument.split(PREFIX_DEADLINE, 2);
        if (deadlineDetail.length != 2) {
            throw new MissingDateTimeException();
        }
        savedTasks.add(new Deadline(deadlineDetail[0], deadlineDetail[1]));
        printConfirmAdd();
    }

    private static void executeAddEvent(String commandArgument) throws MissingDateTimeException{
        String[] eventDetail = commandArgument.split(PREFIX_EVENT, 2);
        if (eventDetail.length != 2) {
            throw new MissingDateTimeException();
        }
        savedTasks.add(new Event(eventDetail[0], eventDetail[1]));
        printConfirmAdd();
    }

    private static void printConfirmAdd() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t" + "Got it. I've added this task:");
        System.out.println("\t" + savedTasks.get(savedTasks.size() - 1).toString());
        System.out.println("\t" + "Now you have " + savedTasks.size() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    private static String[] parseUserInput(String userInput) {
        final String[] args = userInput.strip().split(" ", 2);
        if (args.length == 2) {
            return args;
        } else {
            return new String[] {args[0], ""};
        }
    }

    private static void executeMark(String commandArgument) throws InvalidIndexException {
        int index = Integer.parseInt(commandArgument) - 1;
        if (index < 0 || index > savedTasks.size() - 1) {
            throw new InvalidIndexException();
        }
        savedTasks.get(index).markAsDone();
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t" + "Nice! I've marked this task as done:");
        System.out.println("\t" + savedTasks.get(index).toString());
        System.out.println(HORIZONTAL_LINE);
    }

    private static void executeUnmark(String commandArgument) throws InvalidIndexException {
        int index = Integer.parseInt(commandArgument) - 1;
        if (index < 0 || index > savedTasks.size() - 1) {
            throw new InvalidIndexException();
        }
        savedTasks.get(index).markAsNotDone();
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t" + "OK, I've marked this task as not done yet:");
        System.out.println("\t" + savedTasks.get(index).toString());
        System.out.println(HORIZONTAL_LINE);
    }

    private static void executeDelete(String commandArgument) throws InvalidIndexException {
        int index = Integer.parseInt(commandArgument) - 1;
        if (index < 0 || index > savedTasks.size() - 1) {
            throw new InvalidIndexException();
        }
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t" + "Noted. I've removed this task:");
        System.out.println("\t" + savedTasks.get(index).toString());
        savedTasks.remove(index);
        System.out.println("\t" + "Now you have " + savedTasks.size() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    private static void executeList() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t" + "Here are the tasks in your list:");
        for (int i = 0; i < savedTasks.size(); i++) {
            int index = i + 1;
            System.out.println("\t" + index + "." + savedTasks.get(i).toString());
        }
        System.out.println(HORIZONTAL_LINE);
    }

    private static void executeExit() throws IOException {
        Storage.writeTaskToFile(savedTasks);
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
        System.exit(0);
    }

    private static void printGreeting() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t" + "Hello! I'm Duke");
        System.out.println("\t" + "What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }
}
