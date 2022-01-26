package funbox.command;

import funbox.exception.FunBoxExceptions;
import funbox.util.Ui;
import funbox.util.TaskList;
import funbox.util.Storage;

public class UnmarkCommand extends Command {
    int index;
    public UnmarkCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws FunBoxExceptions {
        if ((index - 1) > taskList.getSize() || (index - 1) < 0) {
            throw new FunBoxExceptions("Wrong index!");
        } else {
            taskList.setTaskUndone(this.index - 1);
        }
    }
}
