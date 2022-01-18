import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_LINE = "\t" + "____________________________________________________________";

    private static final int MAX_NUMBER_OF_TASKS = 100;
    private static Task[] savedTask = new Task[MAX_NUMBER_OF_TASKS];
    private static int numberOfTasks =  0;

    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";

    private static final String PREFIX_DEADLINE = "/by";
    private static final String PREFIX_EVENT = "/at";

    public static void main(String[] args) {
        printGreeting();

        Scanner sc = new Scanner(System.in);

        while (true) {
            String userInput = sc.nextLine();
            executeCommand(userInput);
        }

    }

    private static void executeCommand(String userInput) {
        String[] command = parseUserInput(userInput);
        String commandType = command[0];
        String commandArgument = command[1];

        switch (commandType) {
            case COMMAND_BYE:
                executeExit();
                break;
            case COMMAND_LIST:
                executeList();
                break;
            case COMMAND_MARK:
                executeMark(commandArgument);
                break;
            case COMMAND_UNMARK:
                executeUnmark(commandArgument);
                break;
            case COMMAND_TODO:
                executeAddTodo(commandArgument);
                break;
            case COMMAND_DEADLINE:
                executeAddDeadline(commandArgument);
                break;
            case COMMAND_EVENT:
                executeAddEvent(commandArgument);
                break;
        }

    }

    private static void executeAddTodo(String commandArgument) {
        savedTask[numberOfTasks] = new Todo(commandArgument);
        printConfirmAdd();
    }

    private static void executeAddDeadline(String commandArgument) {
        String[] deadlineDetail = commandArgument.split(PREFIX_DEADLINE);
        savedTask[numberOfTasks] = new Deadline(deadlineDetail[0], deadlineDetail[1]);
        printConfirmAdd();
    }

    private static void executeAddEvent(String commandArgument) {
        String[] eventDetail = commandArgument.split(PREFIX_EVENT);
        savedTask[numberOfTasks] = new Event(eventDetail[0], eventDetail[1]);
        printConfirmAdd();
    }

    private static void printConfirmAdd() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t" + "Got it. I've added this task:");
        System.out.println("\t" + savedTask[numberOfTasks].toString());
        System.out.println("\t" + "Now you have " + (numberOfTasks + 1) + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
        numberOfTasks++;
    }
    private static String[] parseUserInput(String userInput) {
        final String[] args = userInput.strip().split(" ", 2);
        if (args.length == 2) {
            return args;
        } else {
            return new String[] {args[0], ""};
        }
    }

    private static void executeMark(String commandArgument) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t" + "Nice! I've marked this task as done:");
        int index = Integer.parseInt(commandArgument) - 1;
        savedTask[index].markAsDone();
        System.out.println("\t" + savedTask[index].toString());
        System.out.println(HORIZONTAL_LINE);
    }

    private static void executeUnmark(String commandArgument) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t" + "OK, I've marked this task as not done yet:");
        int index = Integer.parseInt(commandArgument) - 1;
        savedTask[index].markAsNotDone();
        System.out.println("\t" + savedTask[index].toString());
        System.out.println(HORIZONTAL_LINE);
    }
    private static void executeList() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t" + "Here are the tasks in your list:");
        for (int i = 0; i < numberOfTasks; i++) {
            int index = i + 1;
            System.out.println("\t" + index + "." + savedTask[i].toString());
        }
        System.out.println(HORIZONTAL_LINE);
    }

    private static void executeExit() {
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
