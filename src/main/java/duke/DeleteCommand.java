package main.java.duke;

import java.io.IOException;

public class DeleteCommand extends Command {

    int taskToDelete;

    public DeleteCommand(int taskToDelete) {
        this.taskToDelete = taskToDelete;
    }
    public void execute(TaskList tasks,Ui ui, Storage storage) {
        ui.showRemoveTask(tasks.getTask(taskToDelete), tasks.size() - 1);
        tasks.delete(taskToDelete);
        try {
            storage.writeToFile(tasks.getTaskArr());
        } catch (IOException e) {
            ui.showError("IOException");
        }
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
