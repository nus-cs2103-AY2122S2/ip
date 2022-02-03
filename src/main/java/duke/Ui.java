package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.CommandType;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.List;

public class Ui {

    /**
     * Represents messages, formatter of messages and printer for Duke application.
     *
     * @author Zheng Teck
     * @version 1.0
     */
    private Ui() {

    }

    public static final String MSG_EMPTYINPUT = "☹ OOPS!!! I'm sorry, input cannot be empty!";

    public static final String MSG_INVALDTASKIDFORMAT = "☹ OOPS!!! I'm sorry, task Id should be a number. " + "You can use the list command to find the tasks.";

    public static final String MSG_INVALIDCMD = "☹ OOPS!!! I'm sorry, but I don't know what that means!";

    public static final String MSG_INVALIDTASKID = "☹ OOPS!!! I'm sorry, task not found! Please select a task in " + "range. You can use the list command to find the tasks.";

    public static final String MSG_INVLIADCMDFORMAT = "☹ OOPS!!! I'm sorry, your formatting for your message isn't right!";

    public static final String MSG_TASKALREADYDONE = "☹ OOPS!!! Task already completed!";

    public static final String MSG_TASKNOTCOMPLETE = "☹ OOPS!!! Task not yet completed!";

    public static final String MSG_EMPTYTASK = "☹ OOPS!!! No task exists!";

    public static final String MSG_FILEREADERROR = "☹ OOPS!!! There was an error loading the file.";

    public static final String MSG_INVALIDDATETIMEFORMAT = "☹ OOPS!!! Wrong format for Date or Time. " + "Please use [dd-MM-yyyy HH:mm] format. i.e. 30-05-1959 13:59";

    public static final String MSG_INVALIDDATETIME = "☹ OOPS!!! You can't set a task in the past!";

    public static final String MSG_FILEWRITEERROR = "☹ OOPS!!! We can't save your records! This is likely due to write permissions on your system.";

    public static final String MSG_NOMATCH = "☹ OOPS!!! We can't find anything!";

    private static final String MSG_LINE_SEPARATOR = "____________________________________________________________\n";

    /**
     * Print welcome message.
     */
    public static void printWelcome() {
        print("""
                Hello! I'm DatoDato! Your personal helper bot. :)\s
                (Hint: Type help and hit enter for assistance)\s
                What can I do for you?""");
    }

    /**
     * Print exit message
     */
    public static void printExit() {
        print("Bye! Checkout another cool bot @KatoKatoBot on Telegram.\n" + "Hope to see you again soon!");
    }

    public static void printHelp() {
        StringBuffer out = new StringBuffer("Hi below are the list of commands available in the current version. \n\n");
        out.append(AddCommand.usage());
        out.append(ListCommand.usage());
        out.append(FindCommand.usage());
        out.append(DoneCommand.usage());
        out.append(DeleteCommand.usage());
        out.append(ExitCommand.usage());
        print(out.toString().trim());
    }

    /**
     * Formats the task list for printing.
     *
     * @param taskList The list of task in the Duke application.
     * @return The formatted String to be printed.
     */
    public static String taskListMsg(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTaskList();
        StringBuffer result = new StringBuffer("Here are the tasks in your list: \n\n");
        for (int i = 1; i <= tasks.size(); i++) {
            result.append(String.format("%d.%s", i, tasks.get(i - 1)));
            if (i != tasks.size()) {
                result.append("\n");
            }
        }
        return result.toString();
    }

    /**
     * Formats the task complete message for printing.
     *
     * @param description Description of the task.
     * @return The formatted String to be printed.
     */
    public static String updateTaskMsg(String description) {
        return "Alright! I've updated the task: \n  " + description;
    }

    /**
     * Formats the task delete message for printing.
     *
     * @param description Description of task deleted.
     * @param totalTask Total number of task in the task list.
     * @return The formatted String to be printed.
     */
    public static String deleteTaskMsg(String description, int totalTask) {
        return String.format("Noted. I've removed this task: \n  %s\nNow you have %d tasks in the list.", description, totalTask);
    }

    /**
     * Formats the task added message for printing.
     *
     * @param description Description of task stored.
     * @param totalTask Total number of task in the task list.
     * @return The formatted String to be printed.
     */
    public static String addTaskMsg(String description, int totalTask) {
        return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", description, totalTask);
    }

    /**
     * Print message in Duke application format.
     *
     * @param input String value to be printed.
     */
    public static void print(String input) {
        System.out.println(MSG_LINE_SEPARATOR + input + "\n" + MSG_LINE_SEPARATOR);
    }
}
