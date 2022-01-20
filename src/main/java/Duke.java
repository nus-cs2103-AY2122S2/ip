import java.util.ArrayList;
import java.util.Scanner;

/**
 * Duke class represents a Personal Assistant Chatbot that
 * helps a person to keep track of various things.
 */
public class Duke {
    protected String lineBreak = "____________________________________________________________\n";
    protected String catFace = " =^_^=\n";
    protected String logo = " ____        _\n"
            + "|  _ \\ _   _| | _____      /\\_/\\           ___\n"
            + "| | | | | | | |/ / _      = o_o =_______    \\ \\  -Julie Rhodes-\\\n"
            + "| |_| | |_| |   <  __/     __^      __(  \\.__) )\n"
            + "|____/ \\__,_|_|\\_\\___| (@)<_____>__(_____)____/\n";
    protected ArrayList<Task> tasks;

    public Duke() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Prints the greeting and logo.
     */
    public void greet() {
        System.out.print(logo);
        printMessage("Meow! I'm Duke!\nWhat can I do for you?");
    }

    /**
     * Prints the farewell.
     */
    public void exit() {
        printMessage("Bye. Meow!");
    }

    /**
     * @param message The message to be printed.
     */
    private void printMessage(String message) {
        System.out.print(lineBreak + message + catFace + lineBreak);
    }

    private void listTasks() {
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            message.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        message.append("Number of tasks in list: ").append(tasks.size());
        printMessage(message.toString());
    }

    private void deleteTask(String input) {
        try {
            int index = Integer.parseInt(input);
            Task t = tasks.get(index - 1);
            tasks.remove(index - 1);
            printMessage("Meow! Task is removed!\n" + t + "\n" + "Number of tasks in list: " + tasks.size());
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            printMessage(ErrorMessage.MESSAGE_INVALID_TASK);
        }
    }

    private void toggleTaskStatus(String input, CommandType command) {
        try {
            int index = Integer.parseInt(input);
            if (command.equals(CommandType.MARK)) {
                tasks.get(index - 1).markAsDone();
            } else {
                tasks.get(index - 1).unmarkAsDone();
            }
            System.out.print(lineBreak + "Meow! Task is done!" + catFace
                    + tasks.get(index - 1) + "\n" + lineBreak);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            printMessage(ErrorMessage.MESSAGE_INVALID_TASK);
        }
    }

    private void addTask(String[] args, CommandType command) {
        Task t;
        switch (command) {
        case DEADLINE:
            t = new Deadline(args[0], args[1]);
            break;
        case EVENT:
            t = new Event(args[0], args[1]);
            break;
        default:
            t = new Todo(args[0]);
        }
        tasks.add(t);
        printMessage("Meow! Task is added!\n" + t + "\n"
                + "Number of tasks in list: " + tasks.size());
    }

    /**
     * Processes the command from the user.
     * @param command User command.
     * @param args Task arguments.
     */
    private void processCommand(CommandType command, String[] args) {
        switch (command) {
        case LIST:
            listTasks();
            break;
        case DELETE:
            deleteTask(args[0]);
            break;
        case MARK:
            toggleTaskStatus(args[0], CommandType.MARK);
            break;
        case UNMARK:
            toggleTaskStatus(args[0], CommandType.UNMARK);
            break;
        case DEADLINE:
            addTask(args, CommandType.DEADLINE);
            break;
        case EVENT:
            addTask(args, CommandType.EVENT);
            break;
        case TODO:
            addTask(args, CommandType.TODO);
        }
    }

    public void runChatbot() {
        Scanner sc = new Scanner(System.in);
        Parser ps = new Parser();
        String input = sc.nextLine().strip();

        while (!input.equals("bye")) {
            try {
                CommandType command = ps.parseCommand(input);
                String[] args = ps.parseInput(input, command);
                processCommand(command, args);
            } catch (DukeException e) {
                printMessage(e.toString());
            } finally {
                input = sc.nextLine().strip();
            }
        }

        sc.close();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        duke.runChatbot();
        duke.exit();
    }
}