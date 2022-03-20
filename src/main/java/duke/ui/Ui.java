package duke.ui;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

public class Ui {
    public static final String PRIMARY_COLOR = "#2C3531";
    public static final String SECONDARY_COLOR = "#116466";
    public static final String TERTIARY_COLOR = "#D1E8E2";

    public static final String DIVIDER = "    ____________________________________________________________";
    public static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter
            .ofPattern("HH:mm EEEE, MM-dd-yyyy").localizedBy(Locale.ENGLISH);
    public static final String BOT_NAME = "duke";

    private String nextMessage;

    /**
     * Instantiates an empty Ui object.
     */
    public Ui() {
        nextMessage = "";
    }

    /**
     * Returns the nextMessage variable.
     *
     * @return String nextMessage instance variable
     */
    public String getNextMessage() {
        return nextMessage;
    }

    /**
     * Sets the message to be printed to the argument.
     *
     * @param nextMessage String the new message to be printed.
     */
    public void setNextMessage(String nextMessage) {
        this.nextMessage = nextMessage;
    }

    /**
     * Prints the UI for the start of the program.
     */
    public static String showUiForStart() {
        String result = "Hello, I'm " + BOT_NAME + ".\n";
        result += "What can I do for you?";
        return result;
    }

    /**
     * Prints the UI for the end of the program.
     */
    public String showUiForBye() {
        String result = "Bye. Hope to see you again soon!";
        nextMessage = result;
        return result;
    }

    /**
     * Prints the divider.
     */
    public static void printDivider() {
        System.out.println(DIVIDER);
    }

    /**
     * Prints out every Task in the task list.
     *
     * @param taskList TaskList to be printed out.
     */
    public String showUiForTaskList(TaskList taskList) {
        assert taskList != null;
        assert taskList.size() != 0;
        String result = taskList.toString();
        nextMessage = result;
        return result;
    }

    /**
     * Prints out the error.
     *
     * @param de DukeException any errors.
     * @return toString version of the error.
     */
    public String showUiForError(DukeException de) {
        String result = "Error | " + de.toString();
        nextMessage = result;
        return result;
    }

    /**
     * Prints the UI for adding a task.
     *
     * @param taskObj    Task.
     * @param listLength int Length of the task list.
     */
    public String showUiForAdd(Task taskObj, int listLength) {
        assert taskObj != null;
        String result = "Adding a task: ";
        result += taskObj.toString() + "\n";
        if (listLength == 1) {
            result += "Now you got " + (listLength) + " task in the list!";
        } else {
            result += "Now you got " + (listLength) + " tasks in the list!";
        }
        nextMessage = result;
        return result;
    }

    /**
     * Prints the UI for deleting a task.
     *
     * @param taskObj    Task.
     * @param listLength int Length of the task list.
     */
    public String showUiForDelete(Task taskObj, int listLength) {
        assert taskObj != null;
        String result = "Ok, removing a task: \n";
        result += taskObj.toString() + "\n";
        if (listLength == 1) {
            result += "Now you got " + (listLength) + " task in the list!";
        } else {
            result += "Now you got " + (listLength) + " tasks in the list!";
        }
        nextMessage = result;
        return result;
    }

    /**
     * Shows all elements in the sorted list.
     *
     * @param taskList ArrayList of Task objects to be printed.
     * @param sortType Type of sorting based on the enum.
     */
    public String showUiForSort(TaskList taskList, TaskList.SortType sortType) {
        assert taskList != null;
        assert taskList.size() != 0;
        String result = "Sorting your tasks by " + sortType.toString() + ":\n";
        result += taskList.toString();
        nextMessage = result;
        return result;
    }

    /**
     * Shows the Ui when adding a tag to a task.
     *
     * @param tagName String Name of the tag to be added.
     * @param task    Task Task object.
     * @return String String for adding a tag.
     */
    public String showUiForTag(String tagName, Task task) {
        String result = "Adding the following Tag #" + tagName + " to " + task.toString();
        nextMessage = result;
        return result;
    }

    /**
     * Shows the Ui when deleting a tag to a task.
     *
     * @param tagName String Name of the tag to be deleted.
     * @param task    Task Task object.
     * @return String String for adding a tag.
     */
    public String showUiForUntag(String tagName, Task task) {
        String result = "Deleting the following Tag #" + tagName + " from " + task.toString();
        nextMessage = result;
        return result;
    }
}
