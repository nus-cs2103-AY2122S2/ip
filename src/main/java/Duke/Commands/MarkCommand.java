package Duke.Commands;

import Duke.Managers.TaskList;
import Duke.Managers.Ui;
import Duke.Managers.Storage;
import Duke.Tasks.Task;
import Duke.Exception.DukeException;

public class MarkCommand extends Command {
    protected boolean isMark;
    protected int index;

    public MarkCommand(Boolean isMark, int index) {
        this.isMark = isMark;
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui io, Storage storage) throws DukeException {
        if(index >= taskList.getSize() || index < 0)
            throw new DukeException("Invalid input! This task number does not exist.");
        String variance = isMark ? "done" : "undone";
        String output = "Done! I've marked this task as " + variance + "\n       ";
        Task task = taskList.get(index);
        task.setCompleted(isMark);
        io.showMessage(output + task.toString());
        storage.save(taskList);
    }
}
