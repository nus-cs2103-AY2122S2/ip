package command;

import task.TaskList;
import tsundere.Storage;
import tsundere.TsundereException;

/**
 * Delete task in tasklist and storage saves it
 */
public class DeleteCommand extends Command {
    protected int num;
    public DeleteCommand(int num) {
        this.num = num;
    }

    /**
     * Delete command to add the task into the tasklist and store it using the Storage
     *
     * @param t TaskList for managing and adding tasks
     * @param u UI for displaying text
     * @param s Storage for saving to file
     * @throws TsundereException exception for invalid number
     */
    public String execute(TaskList t, Storage s) throws TsundereException {
        if (t.getCount() < this.num || this.num <= 0) {
            throw new TsundereException("Your number is not valid!!!");
        }
        String rs = ("Deleting the following task. You're not being lazy, are you?\n" + t.getTaskStr(this.num));
        t.delete(this.num);
        s.saveFile(t.tasksToString());
        return rs;
    }

    public boolean isExit() {
        return false;
    }
}
