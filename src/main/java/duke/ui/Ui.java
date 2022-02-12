package duke.ui;

import duke.tasks.Task;

/**
 * Handles all return messages from commands
 */
public class Ui {
    public static final String INDENT = "     ";
    public static final String LINE = "________________________________\n";
    public Ui() {
    }

    /**
     * Returns welcome message
     * @return welcome message
     */
    public String welcomeMessage() {
        return INDENT + "I'm Mr MeeTrack, look at me!!!";
    }

    /**
     * returns addCommand response on successful add of task
     * @param added task added to list
     * @param size size of list after adding
     * @return message for successful add
     */
    public String addMessage(Task added, int size) {
        return INDENT + "Yes sireeee!. I've added this task:\n"
            + INDENT + "  " + added + "\n"
            + INDENT + "Now you have " + size + " tasks in the list";
    }

    /**
     * returns exit message
     * @return exit message
     */
    public String byeMessage() {
        return INDENT + "Bye. Hope to see you again soon!";
    }

    /**
     * returns deleteCommand response on successful deletion of task
     * @param deleted tasks deleted
     * @param size of list after deletion
     * @return message for successful deletion
     */
    public String deleteMessage(Task deleted, int size) {
        return INDENT + "Oooh Ok!. I've removed this task:\n"
            + INDENT + "  " + deleted + "\n"
            + INDENT + "Now you have " + size + " tasks in the list";
    }

    /**
     * returns markCommand response on successful marking of task
     * @param marked task marked
     * @return message for successful mark
     */
    public String markMessage(String marked) {
        return INDENT + "Nice! I've marked this task as done:\n"
            + INDENT + marked;
    }

    /**
     * returns ynMarkCommand response on successful marking of task
     * @param unMarked task un-marked
     * @return message for successful un-mark
     */
    public String unMarkMessage(String unMarked) {
        return INDENT + "I've marked this task as not done yet:\n"
            + INDENT + unMarked;
    }

    /**
     * returns findCommand response on finding tasks
     * @param isFound if a task with matching keyword is found
     * @return message on whether a task with matching keyword is found
     */
    public String findMessage(boolean isFound) {
        if(isFound) {
            return INDENT + "Here are the matching tasks in your list\n";
        } else {
            return "There are no tasks matching in your list";
        }
    }

    /**
     * returns updateCommand response on successful update of task
     * @return message for successful update of task
     */
    public String updateMessage() {
        return INDENT + "Ooohhh K! I have updated your task\n";
    }

    /**
     * returns listCommand response on listing of task
     * @return message for listing tasks
     */
    public String listMessage() {
        return INDENT + "Here are the tasks in your list:\n";
    }
}
