package dazz.command;

import dazz.Storage;
import dazz.TaskList;
import dazz.Ui;
import dazz.exception.InvalidTaskIndexException;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidTaskIndexException {
        taskList.mark(this.index);
//        ui.showMark(taskList.getTask(this.index));
        String message = ui.messageForMark(taskList.getTask(this.index));
        storage.updateList(taskList);
        return message;
    }
}
