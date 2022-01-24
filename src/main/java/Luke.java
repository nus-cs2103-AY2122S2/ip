import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Luke {
    public static final String BOT_NAME = "Luke";
    private static final String LOGO = "  _           _        \n"
            + " | |         | |       \n"
            + " | |    _   _| | _____ \n"
            + " | |   | | | | |/ / _ \\\n"
            + " | |___| |_| |   <  __/\n"
            + " |______\\__,_|_|\\_\\___|\n";
    private TaskList taskList;
    private StorageFile storageFile;

    Luke(String filePath) {
        taskList = new TaskList();
        try {
            storageFile = new StorageFile(filePath);
        } catch (IOException e) {
            printOutput(String.format("Oops, there is something wrong with the storage...\n" +
                    "The error is:\n%s\nGoodbye...", e.getMessage()));
            System.exit(1);
        }
    }

    public void greeting() {
        System.out.println("Hello! I am \n" + LOGO);
        printOutput("How can I help you?");
    }

    public void farewell() {
        printOutput("I'll take my leave... Do you know who is my father?");
    }

    public void printTaskList() {
        if (!taskList.isEmpty()) {
            String msg = "Currently, you have the following tasks:\n";
            for (int i = 0; i < taskList.size(); i++) {
                msg += String.format("\t%d. %s\n", i + 1, taskList.get(i));
            }
            printOutput(msg.stripTrailing());
        } else {
            printOutput("Yay! You have no task to do!");
        }
    }

    public void printOutput(String output) {
        String msg = "============================================================\n"
                + String.format("%s\n", output)
                + "============================================================";
        System.out.println(msg);
    }

    public void initializeStorage() {
        try {
            List<String> data = storageFile.load();
            if (!data.isEmpty()) {
                for (String str : data) {
                    String[] inputs = str.split("\\|");
                    Command cmd = Command.parseCommand(inputs[0]);
                    if (cmd.getCommandAction().equals(CommandAction.UNKNOWN)) {
                        printOutput("Oops, the storage is corrupted... Unable to retrieve data.");
                    } else {
                        Task task = parseAddCommand(cmd);
                        if (Integer.parseInt(inputs[1].strip()) == 1) {
                            task.markAsDone();
                        }
                        taskList.add(task);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            printOutput(String.format("Oops, there is something wrong with the storage...\n" +
                    "The error is:\n%s\nGoodbye...", e.getMessage()));
            System.exit(1);
        }
    }

    public void start() {
        initializeStorage();
        Command cmd = new Command();
        Scanner sc = new Scanner(System.in);
        greeting();
        do {
            try {
                cmd = Command.parseCommand(sc.nextLine());
                switch (cmd.getCommandAction().getCommandActionType()) {
                case READ:
                    printTaskList();
                    break;
                case ADD:
                    Task task = parseAddCommand(cmd);
                    taskList.add(task);
                    printOutput(String.format("I have added the following task into list: \n\t%s\nnow you have %d tasks in the list.", task, taskList.size()));
                    break;
                case UPDATE:
                    int index = Integer.parseInt(cmd.getArgumentByKey("index")) - 1;
                    switch (cmd.getCommandAction()) {
                    case MARK:
                        taskList.get(index).markAsDone();
                        printOutput("Using the force... Great! I have forced this task as done.");
                        break;
                    case UNMARK:
                        taskList.get(index).unmarkAsDone();
                        printOutput("Force should be used for greater good!\nI've forced this task as not done yet...");
                        break;
                    case DELETE:
                        Task removedTask = taskList.remove(index);
                        printOutput(String.format("Forcing it out... Success! I've removed the following task:\n\t%s", removedTask));
                    }
                    break;
                case ERROR:
                    printOutput("Oops, the force does not support this action.\nPlease try again :(");
                    break;
                }
                storageFile.save(taskList);
            } catch (IndexOutOfBoundsException e) {
                printOutput("Oops, the force cannot find the task.\nPlease try again :(");
            } catch (NumberFormatException e) {
                printOutput("Oops, the force cannot convert the value to a number.\nPlease try again :(");
            } catch (IllegalArgumentException e) {
                printOutput(String.format("Oops, the force is not strong.\n%s\nPlease try again :(", e.getMessage()));
            } catch (IOException e) {
                printOutput(String.format("Oops, the force is unable to write to the file.\nThe error is:\n%s", e.getMessage()));
            } catch (DateTimeParseException e) {
                printOutput("Oops, the force does not comprehend the date.\nPlease try again :(");
            }
        } while (!cmd.isExitCmd());
        farewell();
        sc.close();
    }

    private Task parseAddCommand(Command cmd) {
        Task task;
        switch (cmd.getCommandAction()) {
        case DEADLINE:
            task = new Deadline(cmd.getArguments());
            break;
        case EVENT:
            task = new Event(cmd.getArguments());
            break;
        default:
            task = new ToDo(cmd.getArguments());
            break;
        }
        return task;
    }

    public static void main(String[] args) {
        Luke luke = new Luke("data/luke.txt");
        luke.start();
    }
}
