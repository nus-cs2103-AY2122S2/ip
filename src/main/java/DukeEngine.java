import java.io.IOException;
import java.util.ArrayList;

public class DukeEngine {
    
    private ArrayList<Task> itemList;
    public boolean isPolling;

    public DukeEngine() {
        itemList = Storage.readSaveFile();
        isPolling = true;
        System.out.println(chatBox(greetingMessage()));
    }

    public void inputHandler(String input) {
        String[] commandArgs = input.split(" ", 2);
        String command = commandArgs[0];
        String commandDetails = commandArgs.length == 2 ? commandArgs[1] : null;

        String replyMessage = "";
        try {
            switch (command) {
            case "bye":
                replyMessage = byeMessage();
                break;

            case "list":
                replyMessage = listItems();
                break;

            case "mark":
                assertValidItemNumber(commandDetails);
                replyMessage = markItem(Integer.parseInt(commandDetails));
                Storage.updateTaskFile(itemList);
                break;

            case "unmark":
                assertValidItemNumber(commandDetails);
                replyMessage = unmarkItem(Integer.parseInt(commandDetails));
                Storage.updateTaskFile(itemList);
                break;

            case "todo":
                //Fall Through
            case "deadline": 
                //Fall Through
            case "event":
                assertNonEmptyDetails(commandDetails);
                Task task = createTask(command, commandDetails);
                replyMessage = addTask(task);
                Storage.updateTaskFile(itemList);
                break;

            case "delete":
                assertValidItemNumber(commandDetails);
                replyMessage = deleteItem(Integer.parseInt(commandDetails));
                Storage.updateTaskFile(itemList);
                break;

            default:
                replyMessage = "OoPs! I don't know what that means :P";
            }
        } catch (DukeException e) {
            replyMessage = e.getMessage();
        } catch (IOException ioException) {
            replyMessage = "Something went wrong with the hard disk!";
        }

        System.out.println(chatBox(replyMessage));
    }

    public Task createTask(String command, String commandDetails) throws DukeException{
        String[] taskArgs = null;

        if (command.equals("todo")) {
            return new ToDo(commandDetails);
        } else if (command.equals("deadline")) {
            taskArgs = commandDetails.split(" /by ", 2);
        } else if (command.equals("event")) {
            taskArgs = commandDetails.split(" /at ", 2);
        }

        if (taskArgs.length < 2) throw new DukeException(String.format(
            "Missing details for %s!", command));

        return command.equals("deadline") 
            ? new Deadline(taskArgs[0], taskArgs[1])
            : new Event(taskArgs[0], taskArgs[1]);
    }

    public String addTask(Task task) {
        itemList.add(task);
        return String.format("Got it. I've added this task:\n  %s\nNow you have %s task(s) in the list",
            task, itemList.size());
    }

    public String deleteItem(int itemNumber) {
        Task task = itemList.remove(itemNumber - 1);
        return String.format(
            "Noted. I've removed this task:\n  %s\nNow you have %s task(s) in the list",
            task, itemList.size());
    }

    public String markItem(int itemNumber) {
        Task task = itemList.get(itemNumber - 1);
        task.markAsDone();
        return "Nice! I've marked this as done:\n  " + task;
    }

    public String unmarkItem(int itemNumber) {
        Task task = itemList.get(itemNumber - 1);
        task.unmarkAsDone();
        return "OK, I've marked this task as not done yet:\n  " + task;
    }

    public String listItems() {
        StringBuilder sb = new StringBuilder();
        if (itemList.isEmpty()) {
            sb.append("There is nothing in the list!");
        }
        
        for (int i = 1; i <= itemList.size(); i++) {
            sb.append(i + ". ").append(itemList.get(i - 1)).append("\n");
        }
        return sb.toString();
    }

    public String greetingMessage() {
        StringBuilder greeting = new StringBuilder();
        greeting.append("Wow! Hello! I'm Duke.\n");
        greeting.append("What can I do for you?");

        return greeting.toString();
    }

    public String echoInput(String msg) {
        return msg;
    }

    public String byeMessage() {
        isPolling = false;
        return "Bye. Hope to see you again soon!";
    }

    private void assertNonEmptyDetails(String details) throws DukeException {
        if (details == null) throw new DukeException("Missing details!");
    }

    private boolean isNumeric(String string) {
        try {
            Integer.parseInt(string);
        } catch (NumberFormatException exception) {
            return false;
        }
        return true;
    }

    private void assertValidItemNumber(String str) throws DukeException {
        if (str == null) throw new DukeException("Missing item number!");
        
        if (!isNumeric(str)) throw new DukeException(
            "Please specify a numerical value for the item number instead of \"" + str + "\"!");

        int itemNumber = Integer.parseInt(str);

        if (itemNumber <= 0 || itemNumber > itemList.size()) throw new DukeException(
            "Please specify a valid item number");
    }

    //wraps a given text in a box to be printed
    public String chatBox(String givenText) {
        StringBuilder box = new StringBuilder();
        box.append("----------------------------------------\n");
        box.append(givenText);

        //add a newline after givenText if it does not have one
        if (givenText.charAt(givenText.length() - 1) != '\n') box.append("\n");

        box.append("----------------------------------------\n");

        return box.toString();
    }

}
