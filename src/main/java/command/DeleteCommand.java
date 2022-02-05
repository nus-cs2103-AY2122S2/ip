package command;

import task.TaskList;
import tsundere.Storage;
import tsundere.TsundereException;

/**
 * Deletes task in tasklist and saves it in storage.
 */
public class DeleteCommand extends Command {
    protected int num;
    public DeleteCommand(int num) {
        this.num = num;
    }

    /**
     * Deletes the task in the tasklist and stores it using the storage.
     *
     * @param t TaskList for managing and adding tasks.
     * @param s Storage for saving to file.
     * @throws TsundereException exception for invalid number.
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

    /**
     * Determines if the class is ExitCommand.
     *
     * @return false always because it is not an ExitCommand.
     */
    public boolean isExit() {
        return false;
    }
}
