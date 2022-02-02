package command;

import task.TaskList;
import tsundere.Storage;
import tsundere.Ui;

/**
 * Find tasks that contain user input keyword
 */
public class FindCommand extends Command {

    protected String body;

    /**
     * Create a new FindCommand class
     *
     * @param s userinput keyword
     */
    public FindCommand(String s) {
        this.body = s;
    }

    /**
     * Execute finding of keyword by using t.findTask
     *
     * @param t TaskList for managing and adding tasks
     * @param u UI for displaying text
     * @param s Storage for saving to file
     */
    public void execute(TaskList t, Ui u, Storage s) {
        String foundTasks = t.findTask(this.body);

        if (foundTasks.equals("")) {
            u.wrapText("Cannot find the task you want!");
        } else {
            u.wrapText("Found the task(s)! I would "
                    + "appreciate a thanks or that i need it or anything." + foundTasks);
        }
    }

    /**
     * Always return false as it is not an ExitCommand
     *
     * @return false
     */
    public boolean isExit() {
        return false;
    }
}
