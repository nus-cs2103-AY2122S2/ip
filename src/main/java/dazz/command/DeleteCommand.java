package dazz.command;

import dazz.Storage;
import dazz.TaskList;
import dazz.Ui;
import dazz.exception.InvalidTaskIndexException;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidTaskIndexException {
        String message = ui.messageForDelete(taskList.getTask(this.index));
//        ui.showDelete(taskList.getTask(this.index));
        taskList.delete(this.index);
        storage.updateList(taskList);
        return message;
    }
}
