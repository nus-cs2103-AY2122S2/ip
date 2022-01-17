import java.util.ArrayList;
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
    private List<Task> taskList;

    Luke() {
        taskList = new ArrayList<>();
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

    public void start() {
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
                    default:
                        break;
                }
            } catch (UnknownCommandException e) {
                printOutput("Oops, the force does not support this action.\nPlease try again :(");
            } catch (IndexOutOfBoundsException e) {
                printOutput("Oops, the force cannot find the task.\nPlease try again :(");
            } catch (NumberFormatException e) {
                printOutput("Oops, the force cannot convert the value to a number.\nPlease try again :(");
            } catch (IllegalArgumentException e) {
                printOutput(String.format("Oops, the force is not strong.\n%s\nPlease try again :(", e.getMessage()));
            }
        } while (!cmd.isExitCmd());
        farewell();
        sc.close();
    }

    public static void main(String[] args) {
        Luke luke = new Luke();
        luke.start();
    }
}
