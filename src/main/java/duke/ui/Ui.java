package duke.ui;

import duke.tasks.Task;

/**
 * Class which handles all printing and formatting for messages
 */
public class Ui {
    public static final String INDENT = "     ";
    public static final String LINE = "________________________________\n";
    public Ui() {
    }

    /**
     * prints welcome message when duke.Duke is run
     */
    public String welcomeMessage() {
        return INDENT + "I'm Mr MeeTrack, look at me!!!";
    }

    public String addMessage(Task added, int size) {
        return INDENT + "Yes sireeee!. I've added this task:\n"
            + INDENT + "  " + added + "\n"
            + INDENT + "Now you have " + size + " tasks in the list";
    }

    public String byeMessage() {
        return INDENT + "Bye. Hope to see you again soon!";
    }

    public String deleteMessage(Task deleted, int size) {
        return INDENT + "Oooh Ok!. I've removed this task:\n"
            + INDENT + "  " + deleted + "\n"
            + INDENT + "Now you have " + size + " tasks in the list";
    }

    public String markMessage(String marked) {
        return INDENT + "Nice! I've marked this task as done:\n"
            + INDENT + marked;
    }

    public String unMarkMessage(String marked) {
        return INDENT + "I've marked this task as not done yet:\n"
            + INDENT + marked;
    }

    public String findMessage(boolean isFound) {
        if(isFound) {
            return INDENT + "Here are the matching tasks in your list\n";
        } else {
            return "There are no tasks matching in your list";
        }
    }

    public String listMessage() {
        return INDENT + "Here are the tasks in your list:\n";
    }
}
