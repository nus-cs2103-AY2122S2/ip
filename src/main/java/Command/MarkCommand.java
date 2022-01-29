package Command;

import Task.TaskList;

import Main.Ui;
import Main.Storage;

public class MarkCommand extends Command {
    enum Type {
        MARK,
        UNMARK,
    }

    protected Type ty;
    protected int num;

    public MarkCommand(String s, int num) {
        if (s.equals("MARK")) {
            this.ty = Type.MARK;
        } else {
            this.ty = Type.UNMARK;
        }

        this.num = num;
    }

    public void execute(TaskList t, Ui u, Storage s) {
        String say = "";
        switch (ty) {
            case MARK:
                t.markTask(this.num);
                say = "Alright! Aright, i will mark it down!\n";
                break;
            case UNMARK:
                t.unmarkTask(this.num);
                say = "You didn't actually finish?!";
                break;
        }
        u.printWrapper(say + t.getTaskStr(this.num));
        s.saveFile(t.tasksToString());
    }

    public boolean isExit() {
        return false;
    }
}
