package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

public class OutputCommand implements Command{

    private String fullCommand;
    private String[] splitFullCommand;
    private String taskType;

    public OutputCommand(String fullCommand) {
        this.fullCommand = fullCommand;
        this.splitFullCommand = fullCommand.split(" ",2);
        this.taskType = splitFullCommand[0];
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        if (tasks.getTaskSize() == 0) {
            ui.showEmptyListMessage();
        } else {
            ui.showListMessage(tasks);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
