package pikabot;

import pikabot.task.Task;

/**
 * Interacts with the user by printing out statements.
 */
public class Ui {

    public static final String LINE = "";
    public static final String INDENTATION = "";

    /**
     * Prints out welcome message when PikaBot starts running.
     */
    public static String printWelcomeText() {
        String str = "";
        str = str + INDENTATION + LINE + "\n" + INDENTATION + "Hello! I'm PikaBot"
                + "\n" + INDENTATION + "What can I do for you? シ\n" + INDENTATION + LINE;
        return str;
    }

    /**
     * Prints out exit text when PikaBot is closed.
     */
    public static String printClosingText() {
        String str = "";
        str = str + INDENTATION + LINE + "\n" + INDENTATION + "Bye. Hope to see you again!"
                + "\n" + INDENTATION + LINE;
        return str;
    }

    /**
     * Prints out list of tasks.
     *
     * @param taskList TaskList containing tasks to print.
     */
    public static String printListOfTasks(TaskList taskList) {
        StringBuilder sb = new StringBuilder();
        sb.append(INDENTATION).append(LINE).append("\n").append(INDENTATION);
        sb.append("Here are the tasks in your list:").append("\n");

        int taskNumber = 1;
        int length = taskList.noOfTasks();

        while (taskNumber <= length) {
            sb.append(INDENTATION).append(taskNumber).append(".");
            sb.append(taskList.get(taskNumber - 1)).append("\n");
            taskNumber++;
        }
        sb.append(INDENTATION).append(LINE);
        return sb.toString();
    }

    /**
     * Prints out list of tasks containing a specified keyword.
     *
     * @param taskList TaskList containing tasks with the specified keyword.
     * @param keyword Keyword to search for that is given by user.
     */
    public static String printListOfMatchedTasks(TaskList taskList, String keyword) {
        StringBuilder sb = new StringBuilder();

        sb.append(INDENTATION).append(LINE).append("\n");
        sb.append(INDENTATION).append("Here are the tasks containing \"");
        sb.append(keyword).append("\" in your list:").append("\n");

        int taskNumber = 1;
        int length = taskList.noOfTasks();

        while (taskNumber <= length) {
            sb.append(INDENTATION).append(taskNumber).append(".");
            sb.append(taskList.get(taskNumber - 1)).append("\n");
            taskNumber++;
        }

        sb.append(INDENTATION).append(LINE);
        return sb.toString();
    }

    /**
     * Prints out indication that a task has been marked as done.
     *
     * @param task Task that has been marked as done.
     */
    public static String indicateMarked(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append(INDENTATION).append(LINE).append("\n");
        sb.append(INDENTATION).append("Nice! I've marked this task as done:").append("\n");
        sb.append(INDENTATION).append("  ").append(task).append("\n");
        sb.append(INDENTATION).append(LINE);
        return sb.toString();
    }

    /**
     * Prints out indication that a task has been unmarked.
     *
     * @param task Task that has been unmarked.
     */
    public static String indicateUnmarked(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append(INDENTATION).append(LINE).append("\n");
        sb.append(INDENTATION).append("Nice! I've marked this task as not done yet:").append("\n");
        sb.append(INDENTATION).append("  ").append(task).append("\n");
        sb.append(INDENTATION).append(LINE);
        return sb.toString();
    }

    /**
     * Prints out indication that a task has been added.
     *
     * @param task Task that has been added.
     * @param taskList TaskList that task has been added to.
     */
    public static String indicateAddedTask(Task task, TaskList taskList) {
        StringBuilder sb = new StringBuilder();
        sb.append(INDENTATION).append(LINE).append("\n");
        sb.append(INDENTATION).append("Got it. I've added this task:").append("\n");
        sb.append(INDENTATION).append("  ").append(task).append("\n");
        sb.append(INDENTATION).append("Now you have ").append(taskList.noOfTasks());
        sb.append(" tasks in the list.").append("\n").append(INDENTATION + LINE);
        return sb.toString();
    }

    /**
     * Prints out indication that a task has been removed.
     *
     * @param task Task that has been removed.
     * @param taskList TaskList that task has been removed from.
     */
    public static String indicateRemovedTask(Task task, TaskList taskList) {
        StringBuilder sb = new StringBuilder();
        sb.append(INDENTATION).append(LINE).append("\n");
        sb.append(INDENTATION).append("Noted. I've removed this task:").append("\n");
        sb.append(INDENTATION).append("  ").append(task).append("\n");
        sb.append(INDENTATION).append("Now you have ").append(taskList.noOfTasks());
        sb.append(" tasks in the list").append("\n").append(INDENTATION + LINE);
        return sb.toString();
    }

    /**
     * Prints out exception message.
     *
     * @param e Exception caught.
     */
    public static String printExceptionMessage(Exception e) {
        String str = "";
        str = str + INDENTATION + LINE + "\n" + INDENTATION + e.getMessage() + "\n"
                + INDENTATION + LINE;
        return str;
    }

    /**
     * Prints out customised exception message.
     *
     * @param message Customised message.
     */
    public static String printExceptionCustomisedMessage(String message) {
        String str = "";
        str = str + INDENTATION + LINE + "\n" + INDENTATION + message + "\n"
                + INDENTATION + LINE;
        return str;
    }
}
