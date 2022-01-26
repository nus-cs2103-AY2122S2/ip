package duke.command;

import duke.*;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;

public class UnMarkCommand implements Command {

    private String fullCommand;
    private String[] splitFullCommand;
    private int position;

    public UnMarkCommand(String fullCommand) {
        this.fullCommand = fullCommand;
        this.splitFullCommand = this.fullCommand.split(" ", 2);
        this.position = Integer.parseInt(splitFullCommand[1]);
    }

    @Override
    public void execute(TaskList tasks, Storage storage,
                        Ui ui) throws DukeException, IOException {
        if (position < 1 || position > tasks.getTaskSize()) {
            throw new DukeException("duke.task.Task do not exist!");
        } else {
            Task task = tasks.getTask(position);
            if (!task.isDone()) {
                throw new DukeException(
                        "duke.task.Task is already marked as not done!");
            } else {
                task.unmark();
                storage.setInFile(position, task.taskDescriptionForFile());
                ui.showUnMarkMessage(task);
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
