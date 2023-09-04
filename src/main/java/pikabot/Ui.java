package pikabot;

import pikabot.task.Task;

/**
 * Interacts with the user by printing out statements.
 */
public class Ui {


    /**
     * Prints out welcome message when PikaBot starts running.
     */
    public static String printWelcomeText() {
        String str = "";
        str = str + "Hello! I'm PikaBot" + "\n"
                + "What can I do for you? シ\n";
        return str;
    }

    /**
     * Prints out exit text when PikaBot is closed.
     */
    public static String printClosingText() {
        return "Bye. Hope to see you again!";
    }

    /**
     * Prints out list of tasks.
     *
     * @param taskList TaskList containing tasks to print.
     */
    public static String printListOfTasks(TaskList taskList) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:").append("\n");

        int taskNumber = 1;
        int length = taskList.noOfTasks();

        while (taskNumber <= length) {
            sb.append(taskNumber).append(".");
            sb.append(taskList.get(taskNumber - 1)).append("\n");
            taskNumber++;
        }
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

        sb.append("Here are the tasks containing \"");
        sb.append(keyword).append("\" in your list:").append("\n");

        int taskNumber = 1;
        int length = taskList.noOfTasks();

        while (taskNumber <= length) {
            sb.append(taskNumber).append(".");
            sb.append(taskList.get(taskNumber - 1)).append("\n");
            taskNumber++;
        }
        return sb.toString();
    }

    /**
     * Prints out indication that a task has been marked as done.
     *  @param task Task that has been marked as done.
     * @param isMarkCommand Boolean to indicate whether command is to mark or unmark a task.
     */
    public static String indicateEditMark(Task task, boolean isMarkCommand) {
        StringBuilder sb = new StringBuilder();
        if (isMarkCommand) {
            sb.append("Nice! I've marked this task as done:").append("\n");
        } else {
            sb.append("Nice! I've marked this task as not done yet:").append("\n");
        }
        sb.append("  ").append(task);
        return sb.toString();
    }

    /**
     * Prints out indication that a task has been added.
     *  @param task Task that has been added.
     * @param taskList TaskList that task has been added to.
     * @param isAddCommand Boolean to indicate whether command is a command to add or remove task.
     */
    public static String indicateAddOrDeleteTask(Task task, TaskList taskList, boolean isAddCommand) {
        StringBuilder sb = new StringBuilder();
        if (isAddCommand) {
            sb.append("Got it. I've added this task:").append("\n");
        } else {
            sb.append("Noted. I've removed this task:").append("\n");
        }
        sb.append("  ").append(task).append("\n");
        sb.append("Now you have ").append(taskList.noOfTasks());
        sb.append(" tasks in the list.");
        return sb.toString();
    }

    /**
     * Prints out exception message.
     *
     * @param e Exception caught.
     */
    public static String printExceptionMessage(Exception e) {
        return e.getMessage();
    }

    /**
     * Prints out customised exception message.
     *
     * @param message Customised message.
     */
    public static String printExceptionCustomisedMessage(String message) {
        return message;
    }
}
