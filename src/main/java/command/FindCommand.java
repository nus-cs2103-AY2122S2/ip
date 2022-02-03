package command;

import task.TaskList;
import tsundere.Storage;

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
     * @param s Storage for saving to file
     */
    public String execute(TaskList t, Storage s) {
        String[] st = this.body.split(" ");
        String foundTasks = t.findTask(st);

        if (foundTasks.equals("")) {
            return ("Cannot find the task you want!");
        } else {
            return ("Found! Not that i need a thanks or anything." + foundTasks);
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
