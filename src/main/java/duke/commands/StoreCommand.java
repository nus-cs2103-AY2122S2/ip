package duke.commands;

import duke.managers.TaskList;
import duke.managers.Ui;
import duke.managers.Storage;
import duke.tasks.Task;

public class StoreCommand extends Command {
    protected Task task;
    public StoreCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui io, Storage storage) {
        taskList.add(task);
        io.showMessage("Got it. I've added this task:\n       "
                + task.toString()
                + "\n     Now you have " + taskList.getSize() + " task(s) in the list.");
        storage.save(taskList);
    }
}
