package duke.command;

import duke.*;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;

public class DeleteCommand implements Command {

    private String fullCommand;
    private String[] splitFullCommand;
    private int index;

    public DeleteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
        this.splitFullCommand = this.fullCommand.split(" ", 2);
        this.index = Integer.parseInt(splitFullCommand[1]);
    }

    @Override
    public void execute(TaskList tasks, Storage storage,
                        Ui ui) throws DukeException, IOException {
        if (index < 0 || index > tasks.getTaskSize()) {
            throw new DukeException("Task do not exist!");
        } else {
            Task task = tasks.getTask(index);
            tasks.removeTask(index);
            storage.writeToFile(tasks.getTaskList());
            ui.showDeleteMessage(task, tasks.getTaskSize());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
