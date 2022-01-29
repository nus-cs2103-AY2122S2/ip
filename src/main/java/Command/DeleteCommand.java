package Command;

import Main.TsundereException;
import Task.TaskList;
import Main.Ui;
import Main.Storage;

public class DeleteCommand extends Command {
    protected int num;
    public DeleteCommand(int num) {
        this.num = num;
    }

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
