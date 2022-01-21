import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
        this.tasks = initialiseTasks();
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

    private ArrayList<Task> initialiseTasks() {
        FileUtil.createFile();
        ArrayList<Task> tasks = new ArrayList<>();
        List<String> lines = FileUtil.readFromFile();
        if (lines != null) {
            for (String line : lines) {
                String[] args = line.split(" \\| ");
                switch(args[0]) {
                case "D":
                    tasks.add(new Deadline(args[2], args[1].equals("1"), args[3]));
                    break;
                case "E":
                    tasks.add(new Event(args[2], args[1].equals("1"), args[3]));
                    break;
                default:
                    tasks.add(new Todo(args[2], args[1].equals("1")));
                }
            }
        }
        return tasks;
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
            FileUtil.writeTasksToFile(tasks);
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
                System.out.print(lineBreak + "Meow! Task is done!" + catFace
                        + tasks.get(index - 1) + "\n" + lineBreak);
            } else {
                tasks.get(index - 1).unmarkAsDone();
                System.out.print(lineBreak + "Meow! Task is not done!" + catFace
                        + tasks.get(index - 1) + "\n" + lineBreak);
            }
            FileUtil.writeTasksToFile(tasks);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            printMessage(ErrorMessage.MESSAGE_INVALID_TASK);
        }
    }

    private void addTask(String[] args, CommandType command) {
        Task t;
        switch (command) {
        case DEADLINE:
            t = new Deadline(args[0], LocalDate.of(Integer.parseInt(args[1]),
                    Integer.parseInt(args[2]), Integer.parseInt(args[3])));
            break;
        case EVENT:
            t = new Event(args[0],  LocalDate.of(Integer.parseInt(args[1]),
                    Integer.parseInt(args[2]), Integer.parseInt(args[3])));
            break;
        default:
            t = new Todo(args[0]);
        }
        tasks.add(t);
        FileUtil.appendToFile(t.formatForFile());
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
        String input = sc.nextLine().strip();

        while (!input.equals("bye")) {
            try {
                CommandType command = Parser.parseCommand(input);
                String[] args = Parser.parseInput(input, command);
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