package Command;

import Task.TaskList;

import Main.Ui;
import Main.Storage;

public class ExitCommand extends Command {

    public void execute(TaskList t, Ui u, Storage s) {
        u.printWrapper("Finally, you're leaving!\nIt's not like i will miss you or anything...");
    }

    public boolean isExit() {
        return true;
    }
}
