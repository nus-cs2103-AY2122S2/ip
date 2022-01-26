package duke.command;

import duke.*;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;

public class MarkCommand implements Command {

    private String fullCommand;
    private String[] splicedFullCommand;
    private int position;

    public MarkCommand(String fullCommand) {
        this.fullCommand = fullCommand;
        this.splicedFullCommand = this.fullCommand.split(" ", 2);
        this.position = Integer.parseInt(splicedFullCommand[1]);
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException, IOException {
        if (position < 1 || position > tasks.getTaskSize()) {
            throw new DukeException("duke.task.Task do not exist!");
        } else {
            Task task = tasks.getTask(position);
            if (task.isDone()) {
                throw new DukeException("duke.task.Task is already marked as done!");
            } else {
                task.mark();
                storage.setInFile(position, task.taskDescriptionForFile());
                ui.showMarkMessage(task);
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
