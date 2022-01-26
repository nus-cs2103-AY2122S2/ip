package funbox.command;


import funbox.exception.FunBoxExceptions;
import funbox.util.Ui;
import funbox.util.TaskList;
import funbox.util.Storage;

public class FilterCommand extends Command {
    String description;

    public FilterCommand(String description) {
        super(false);
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws FunBoxExceptions {
        taskList.filterTasks(this.description, taskList);
    }
}
