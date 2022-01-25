package duke.commands;

import duke.managers.TaskList;
import duke.managers.Ui;
import duke.managers.Storage;
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
        String variance = isMark ? "done" : "undone";
        String output = "Done! I've marked this task as " + variance + "\n       ";
        io.showMessage(output + taskList.markTask(index, isMark).toString());
        storage.save(taskList);
    }
}
