package Command;

import Main.Storage;
import Main.TsundereException;
import Main.Ui;
import Task.Deadline;
import Task.Event;
import Task.TaskList;
import Task.ToDo;


public class FindCommand extends Command {

    String body;

    public FindCommand(String s) {
        this.body = s;
    }

    public void execute(TaskList t, Ui u, Storage s) throws TsundereException {
        String foundTasks = t.tasksFinder(this.body);

        if (foundTasks.equals("")) {
            u.printWrapper("Cannot find the task you want!");
        } else {
            u.printWrapper("Found the task(s)! I would appreciate a thanks or that i need it or anything." + foundTasks);
        }
    }

    public boolean isExit() {
        return false;
    }
}
