package commands;

import data.Task;
import data.TaskList;
import storage.Storage;
import ui.Ui;

public class DeleteCommand extends Command {
    private final int i;

    public DeleteCommand(int i) {
        this.i = i;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {

            Task t = tasks.get(i);
            tasks.remove(t);
            ui.respond( "Noted. I've removed this task: \n  " +
                    t + "\n     Now you have " + tasks.size() + " tasks in the list.");
        }
    }
