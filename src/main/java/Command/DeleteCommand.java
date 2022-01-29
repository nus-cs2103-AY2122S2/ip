package Command;

import Main.TsundereException;
import Task.TaskList;
import Main.Ui;
import Main.Storage;

/**
 * Delete task in tasklist and storage saves it
 */
public class DeleteCommand extends Command {
    int num;
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
    public void execute(TaskList t, Ui u, Storage s) throws TsundereException {
        if (t.getCount() < this.num || this.num <= 0) {
            throw new TsundereException("Your number is not valid!!!");
        }
        u.printWrapper("Deleting the following task. You're not being lazy, are you?\n" + t.getTaskStr(this.num));
        t.delete(this.num);
        s.saveFile(t.tasksToString());
    }

    public boolean isExit() {
        return  false;
    }
}
