package duke.commands;

import duke.managers.TaskList;
import duke.managers.Ui;
import duke.managers.Storage;
import duke.tasks.Task;
import duke.exceptions.DukeException;

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
