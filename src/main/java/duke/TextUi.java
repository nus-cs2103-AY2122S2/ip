package duke;

import duke.tasks.Task;

/** Represents a TextUI Object that contains the automated responses
 * that Duke will return the user depending on their input.
 */
public class TextUi {
    private static final String DIVIDER = "===================================================";
    private static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Instantiates a ui object.
     */
    public TextUi() {}

    /**
     * Method that greets the user when the file first runs.
     */
    public String greeting() {
        return "Konnichiwassup from\n" + LOGO + showDivider() + "\nWhat do you need help with?\n";
    }

    /**
     * Method that shows a divider to the user.
     */
    public String showDivider() {
        return DIVIDER;
    }

    /**
     * Method that says bye to the user.
     */
    public String sayBye() {
        return "Sayonara! Hope to see you again soon!";
    }

    /**
     * Method that informs the user when a task has been deleted from the task list.
     *
     * @param preview Task that has been deleted from the task list.
     * @return Message that informs the user of a successful deletion of a task.
     */
    public String showDeleteMsg(Task preview) {
        return "Otsukaresamadeshita! You have finally completed one task.\n"
                + preview;
    }

    /**
     * Method that informs the user when a task has been added to the task list.
     *
     * @param taskListSize Updated number of tasks. (Inclusive of the new task added)
     * @return Message that informs the user of a successful addition of a task.
     */
    public String showAddMsg(Integer taskListSize, Task preview) {
        return "We have added " + preview + " to the list. \n" + "You now have a total of "
                + taskListSize + " tasks in your list! Subarashii!";
    }


    /**
     * Method that informs the user when Duke has marked a task as completed.
     *
     * @param preview Task that has been marked as completed.
     * @return Message that informs the user that a task has been marked as completed.
     */
    public String showMarkDoneMsg(Task preview) {
        return "Sugoi! I have marked this task as done!\n"
                + preview;
    }

    /**
     * Method that informs the user when Duke has unmarked a task as not completed.
     *
     * @param preview Task that has been unmarked as not completed.
     * @return Message that informs the user that a task has been unmarked as not completed.
     */
    public String showMarkUndoneMsg(Task preview) {
        return "Daijoubu! I have unmarked this task for you!\n"
                + preview;
    }

    /**
     * Method that displays all tasks that matches the user's description of the task.
     *
     * @param tasks List of tasks found.
     * @return Message that shows the user all the tasks that the program has found.
     */
    public String showFindTaskMsg(String tasks) {
        return "This is what we found! \n" + tasks;
    }

    /**
     * Method that warns the user that their task list is empty.
     *
     * @return A string indicating that the user's task list is empty.
     */
    public String showEmptyMsg() {
        return "Empty Much!";
    }

    /**
     * Method that tells the user that their most recent command has been undone.
     *
     * @param action The type of command that has been undone. (For eg. Add, Delete ... )
     * @return A string telling the user the task that has been undone.
     */
    public String showUndoMsg(String action) {
        return action + " has been undone!";
    }
}
