import java.util.Scanner;
import java.util.ArrayList;

public class ISTJBot {
    private static boolean doneChatting = false;
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static String list = "As an ISTJBot, I present you the task(s) in your list:";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String welcomeMessage = "Hello! I'm ISTJBot. \n" + "What can I do for you?";
        printResponse(welcomeMessage);

        while (!doneChatting) {
            try {
                String request = sc.nextLine();
                String[] requestInfo = request.split(" ");
                Command command = Command.stringToCommand(requestInfo[0]);

                switch (command) {
                case TODO:
                case DEADLINE:
                case EVENT:
                    if (requestInfo.length == 1) {
                        throw new BotException("As an ISTJBot, I cannot find any description for your task.");
                    }
                    addTask(command, requestInfo);
                    // break required for each case
                    break;

                case MARK:
                case UNMARK:
                case DELETE:
                    if (requestInfo.length != 2) {
                        throw new BotException("As an ISTJBot, I cannot modify your task with that command.");
                    }
                    modifyTask(command, Integer.parseInt(requestInfo[1]));
                    break;

                case LIST:
                    if (requestInfo.length > 1) {
                        throw new BotException("As an ISTJBot, I cannot understand more than list.");
                    }

                    if (tasks.size() != 0) {
                        list += "\n";
                    }
                    for (int i = 1; i <= tasks.size(); i++) {
                        list += i != 1 ? "\n" + i + ". " + tasks.get(i - 1).toString() : i + ". " +
                                tasks.get(i - 1).toString();
                    }
                    printResponse(list);

                    // Reset list
                    list = "As an ISTJBot, I present you the task(s) in your list:";
                    break;

                case BYE:
                    if (requestInfo.length > 1) {
                        throw new BotException("As an ISTJBot, I cannot understand more than bye.");
                    }
                    doneChatting = true;
                    printResponse("Bye, I, ISTJBot, will be organizing your tasks until you come back.");
                    sc.close();
                    break;

                }

            } catch(NumberFormatException e) {
                printResponse("As an ISTJBot, I don't think that is a proper index.");

            } catch (BotException e) {
                printResponse(e.getMessage());
            }
        }
    }

    public static void printResponse(String request) {
        String line = "*__________________________________________________________* \n";
        System.out.println(line + request + "\n" + line);
    }

    public static void addTask(Command command, String[] requestInfo) throws BotException {
        String description = "";
        int modifier = -1;
        boolean modifierFound = false;
        String modifierMessage = "";

        for (int i = 1; i < requestInfo.length; i++) {
            if (command == Command.DEADLINE && requestInfo[i].equals("/by")) {
                modifier = i;
                modifierFound = true;
                break;
            } else if (command == Command.EVENT && requestInfo[i].equals("/at")) {
                modifier = i;
                modifierFound = true;
                break;
            }
            description += requestInfo[i] + " ";
        }

        // Error handle for modifier and description
        if (description.equals("")) {
            throw new BotException("As an ISTJBot, I cannot add a task with no description.");
        } else if (!modifierFound && (command == Command.DEADLINE || command == Command.EVENT)) {
            throw new BotException("As an ISTJBot, I cannot add a special task with no timing attached.");
        }

        if (command == Command.DEADLINE || command == Command.EVENT) {
            for (int i = modifier + 1; i < requestInfo.length; i++) {
                modifierMessage += i == requestInfo.length - 1 ? requestInfo[i] : requestInfo[i] + " ";
            }
        }

        // Error handle for modifier message
        if (modifierMessage.equals("") && (command == Command.DEADLINE || command == Command.EVENT)) {
            throw new BotException("As an ISTJBot, I cannot add a special task with no timing attached.");
        }

        Task taskAdded;
        if (command == Command.TODO) {
            taskAdded = new Todo(description);
        } else if (command == Command.DEADLINE) {
            taskAdded = new Deadline(description, modifierMessage);
        } else {
            taskAdded = new Event(description, modifierMessage);
        }
        tasks.add(taskAdded);

        String messageStart = "As an ISTJBot, I will add this task right now. \n";
        String messageLast = "Now you have " + tasks.size() + " ";
        String plural = tasks.size() > 1 ? "tasks" : "task";
        messageLast += plural + " in the list.";
        printResponse(messageStart + taskAdded + "\n" + messageLast);
    }

    public static void modifyTask(Command command, int taskNumber) throws BotException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new BotException("As an ISTJBot, I cannot find a task with that index.");
        }

        Task taskModified = tasks.get(taskNumber - 1);
        switch (command) {
        case MARK:
            tasks.get(taskNumber - 1).mark();
            printResponse("As an ISTJBot, I've marked this task as done: \n" +
                    taskModified.toString());
            break;

        case UNMARK:
            tasks.get(taskNumber - 1).unmark();
            printResponse("As an ISTJBot, I've unmarked this task: \n" +
                    taskModified.toString());
            break;

        case DELETE:
            tasks.remove(taskNumber - 1);
            String messageStart = "As an ISTJBot, I've removed this task: \n";
            String messageLast = "Now you have " + tasks.size() + " ";
            String plural = tasks.size() > 1 ? "tasks" : "task";
            messageLast += plural + " in the list.";
            printResponse(messageStart + taskModified.toString() + "\n" + messageLast);
            break;
        }
    }
}
