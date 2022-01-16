import java.lang.reflect.Array;
import java.util.ArrayList;
/**
 * The FunBoxGear class contains all the functionalities of FunBox.
 */
public class FunBoxGear {
    public final String GREETING = "Yo! I am FunBox [0 _ 0] \nWhat can I do for you?";
    // private Task[] tasksList;
    private ArrayList<Task> tasksList;

    /**
     * Constructor for FunBoxGear
     */
    public FunBoxGear() {
        this.tasksList = new ArrayList<Task>();
    }

    /**
     * Prints out the default GREETING on console
     */
    public void greet() {
        System.out.println(GREETING);
    }

    /**
     * Check whether if user's input is a commands
     *
     * @param message The user's input to the command prompt
     * @return Return false if message is "bye", otherwise return true
     */
    public boolean getCommands(String message) {
        String[] formattedMsg = this.formatCommands(message);
        switch (formattedMsg[0]) {
        case "bye":
            this.sayBye();
            return false;
        case "list":
            this.showList();
            return true;
        case "mark":
            this.markDone(formattedMsg);
            return true;
        case "unmark":
            this.markUndone(formattedMsg);
            return true;
        case "delete":
            this.deleteTask(formattedMsg);
            return true;
        default:
            this.addToList(formattedMsg, formattedMsg[0]);
            return true;
        }
    }

    /**
     * Add user's tasks to the list based on the type of task
     *
     * @param formattedMsg The original message from the users split by " " into an array
     * @param type The type of task: event, deadline, todo
     *
     */
    private void addToList(String[] formattedMsg, String type) {
        String description = this.getMessage(formattedMsg);
        String[] resultArr;
        int noOfItems;
        try {
            switch (type) {
            case "event":
                this.isDescriptionError(description, type);
                resultArr = this.getDescriptionAndDate(description, type);
                this.tasksList.add(new Event(resultArr[0], resultArr[1]));
                break;
            case "deadline":
                this.isDescriptionError(description, type);
                resultArr = this.getDescriptionAndDate(description, type);
                this.tasksList.add(new Deadline(resultArr[0], resultArr[1]));
                break;
            case "todo":
                this.isDescriptionError(description, type);
                this.tasksList.add(new ToDo(description));
                break;
            default:
                throw new FunBoxExceptions("ERROR! I do not know what the commands means :<");
            }
            noOfItems = this.tasksList.size();
            System.out.println("Gotcha! I've added this task!");
            System.out.println(this.tasksList.get(noOfItems - 1));
            System.out.println("Now you have " + noOfItems + " tasks in the list!");
        } catch (FunBoxExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Check whether user input has a description
     *
     * @param description The description of the user input which usually comes after the command type
     * @param type The type of tasks the user used
     * @throws FunBoxExceptions If description == ""
     */
    private void isDescriptionError(String description, String type) throws FunBoxExceptions {
        if (description.equals("")) {
            throw new FunBoxExceptions("ERROR! The description of a " + type + " cannot be empty!");
        }
    }

    /**
     * Loop through a list of items and print out each item
     */
    private void showList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.tasksList.size(); i++) {
            System.out.println((i + 1) + "." + this.tasksList.get(i));
        }
    }

    /**
     * Format the user's message to be able to differentiate between special commands
     *
     * @param message The user's message to be formatted
     * @return Return a String array which contains the split message. The first element is used to differentiate
     * whether it's a message, command, or command which require special formatting
     */
    private String[] formatCommands(String message) {
        // Split message by blank space
        return message.split(" ");
    }

    /**
     * Get the message from the formatted message without the first item of the array which typically contains the
     * command
     *
     * @param formattedMsg The message sent by the user which has been formatted
     * @return Return a string of the original message sent by the users without the command
     *
     */
    private String getMessage(String[] formattedMsg) {
        StringBuilder sb = new StringBuilder();

        int size = formattedMsg.length;

        for (int i = 1; i < size; i++) {
            if (i == size - 1) {
                sb.append(formattedMsg[i]);
            } else {
                sb.append(formattedMsg[i]).append(" ");
            }
        }
        return sb.toString();
    }

    /**
     * Used to split the message, date and time from a message which is needed for certain commands
     *
     * @param message The message to retrieve the date and time from
     * @return Return a String array of size 2 where the first item on the list contains the message and
     * the second item contains the date and time.
     *
     */
    private String[] getDescriptionAndDate(String message, String type) {
        if (type.equals("event")) {
            return message.split(" /at ");
        }
        return message.split(" /by ");
    }

    /**
     * Mark the item on the list as done
     *
     * @param messageArr The formatted message of the user, the second item of the array typically contains the
     *                   taskNo to be mark as done
     *
     */
    private void markDone(String[] messageArr) {
        int taskNo = Integer.parseInt(messageArr[1]);
        this.tasksList.get(taskNo - 1).setDone();
    }

    /**
     * Mark the item on the list as not done
     *
     * @param messageArr The formatted message of the user, the second item of the array typically contains the
     *                   taskNo to be mark as not done
     */
    private void markUndone(String[] messageArr) {
        int taskNo = Integer.parseInt(messageArr[1]);
        this.tasksList.get(taskNo - 1).setUndone();
    }

    /**
     * Delete the task requested by the user
     *
     * @param messageArr The formatted message of the user, the second item of the array typically contains the
     *                   taskNo to be deleted
     */
    private void deleteTask(String[] messageArr) {
        int taskNo = Integer.parseInt(messageArr[1]) - 1;
        Task temp = this.tasksList.get(taskNo);
        this.tasksList.remove(taskNo);
        System.out.println("Noted! I've removed this task:");
        System.out.println(temp);
        System.out.println("Now you have " + this.tasksList.size() + " tasks in the list");
    }

    /**
     * Print out goodbye message to the user
     */
    public void sayBye() {
        System.out.println("B-b-bbye. Hope to see you again soon [0 n 0]");
    }
}
