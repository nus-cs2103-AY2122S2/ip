package duke.ui;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

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
        String result = "";
        ArrayList<Task> tasks = taskList.getTasks();
        for (int index = 0; index < tasks.size(); index++) {
            int order = (index + 1);
            Task task = tasks.get(index);
            if (index == tasks.size() - 1) {
                result += order + ": " + task.toString();
            } else {
                result += order + ": " + task.toString() + "\n";
            }
        }
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
        String result = "Adding a task: ";
        result += taskObj.toString() + "\n";
        result += "Now you got " + (listLength) + " tasks in the list!";
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
        String result = "Ok, removing a task: \n";
        result += taskObj.toString() + "\n";
        result += "Now you got " + (listLength) + " tasks in the list!";
        nextMessage = result;
        return result;
    }

    /**
     * Prints all elements in the sorted list.
     *
     * @param tasks    ArrayList of Task objects to be printed.
     * @param sortType Type of sorting based on the enum.
     */
    public String showUiForSort(ArrayList<Task> tasks, TaskList.SortType sortType) {
        String result = "Sorting your tasks by" + sortType.toString() + ":\n";
        for (Task task : tasks) {
            result += "" + task.toString() + "\n";
        }
        nextMessage = result;
        return result;
    }
}
