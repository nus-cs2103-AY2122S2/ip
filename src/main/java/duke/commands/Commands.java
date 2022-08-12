package duke.commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.tasks.Task;

/**
 * Overarching abstract class that will hold all the required methods of the
 * children command classes.
 */
public abstract class Commands {
    protected String LINE_BREAK = "---------------";
    // find specifics
    protected String FIND_INTRO = "Here are the matching tasks in your list:";
    // bye specifics
    protected String BYE = LINE_BREAK + "\n Byeeeee, come back again ah!\n" + LINE_BREAK;
    // mark specific
    protected String MARK_MESSAGE = "Power la Mr Bosssssss, mark alr bro!";
    // unmark specifics
    protected String UNMARK_MESSAGE = "No probs bro, unmarked already!";
    // intro specific
    protected String LINE_INTRO = "Nah, here's your list";
    // tag specific
    protected String TAG_OUTRO = "Added a tag to: ";

    protected ArrayList<Task> tasks;

    /**
     * Constructor for Commands which does not
     * require an ArrayList as an argument.
     */
    public Commands() {
    }

    /**
     * Constructor for Commands.
     * @param tasks An arrayList of tasks.
     */
    public Commands(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Determines if input string is date or not.
     * @param date
     * @return boolean
     */
    public boolean isDate(String date) {
        try {
            LocalDate.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
