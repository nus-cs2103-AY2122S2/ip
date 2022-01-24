package Command;

import Task.TaskList;
import Main.Ui;
import Main.Storage;

public class DeleteCommand extends Command {
    int num;
    public DeleteCommand(int num) {
        this.num = num;
    }

    public void execute(TaskList t, Ui u, Storage s) {
        u.printWrapper("Deleting the following task. You're not being lazy, are you?\n" + t.getTaskStr(this.num));
        t.delete(this.num);
        s.saveFile(t.tasksToString());
    }

    public boolean isExit() {
        return  false;
    }
}
