package duke;

import tasks.Task;

/** Represents a TextUI Object that contains methods
 * that alters the text display of the GUI
 */
public class TextUi {
    private static final String DIVIDER = "===================================================";
    private static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Instantiates a ui object
     */
    public TextUi() {}

    /**
     * Method that greets the user when the file first runs
     */
    public String greeting() {
        return "Konnichiwassup from\n" + LOGO + showDivider() + "\nWhat do you need help with?\n";
    }

    /**
     * Method that shows a divider to the user
     */
    public String showDivider() {
        return DIVIDER;
    }

    /**
     * Method that says bye to the user
     */
    public String sayBye() {
        return "Sayonara! Hope to see you again soon!";
    }

    /**
     * Method that tells the user a task has been deleted from the task list
     * @param preview task that has been deleted from the task list
     * @return delete task message
     */
    public String showDeleteMsg(Task preview) {
        return "Otsukaresamadeshita! You have finally completed one task.\n"
                + preview;
    }

    /**
     * Method that tells the user a task has been added to the task list
     * @param taskListSize updated number of tasks
     * @return add task message
     */
    public String showAddMsg(Integer taskListSize) {
        return "You now have a total of "
                + taskListSize + " tasks in your list! Subarashii!";
    }


    /**
     * Method that tells the user that Duke has marked a task as done
     * @param preview task that has been marked
     * @return mark task message
     */
    public String showMarkDoneMsg(Task preview) {
        return "Sugoi! I have marked this task as done!\n"
                + preview;
    }

    /**
     * Method that tells the user that Duke has unmarked a task as undone
     * @param preview task that has been unmarked
     * @return unmark task message
     */
    public String showMarkUndoneMsg(Task preview) {
        return "Daijoubu! I have unmarked this task for you!\n"
                + preview;
    }

    /**
     * Method that tells the user that they have found tasks in the task list
     * @param tasks List of tasks found
     * @return Found task string
     */
    public String showFindTaskMsg(String tasks) {
        return "This is what we found! \n" + tasks;
    }

    /**
     * Method that tells the user that their task list is empty
     * @return Empty Tasklist String
     */
    public String showEmptyMsg() {
        return "Empty Much!";
    }
}
