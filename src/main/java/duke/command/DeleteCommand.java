package duke.command;

import duke.*;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;

public class DeleteCommand implements Command {

    private String fullCommand;
    private String[] splicedFullCommand;
    private int position;

    public DeleteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
        this.splicedFullCommand = this.fullCommand.split(" ", 2);
        this.position = Integer.parseInt(splicedFullCommand[1]);
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException, IOException {
        if (position < 0 || position > tasks.getTaskSize()) {
            throw new DukeException("duke.task.Task do not exist!");
        } else {
            Task task = tasks.getTask(position);
            tasks.removeTask(position);
            storage.writeToFile(tasks.getTaskList());
            ui.showDeleteMessage(task, tasks.getTaskSize());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
