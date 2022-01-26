package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class SortCommand extends Command {
    private TaskList.SortType type;
    public SortCommand(TaskList.SortType type, String[] inputArray) {
        super(inputArray);
        this.type = type;
    }

    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) {
        taskList.sortTaskList(this.type);
        storage.save(taskList);
    }

    @Override
    public boolean equals(Object command) {
        SortCommand sortCommand = (SortCommand) command;
        return this.type.equals(sortCommand.type);
    }
}
