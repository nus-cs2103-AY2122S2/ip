package pikabot.command;

import pikabot.TaskList;
import pikabot.Storage;
import pikabot.Ui;

import pikabot.task.Task;

import java.io.IOException;

public class DeleteCommand extends Command {

    String[] deleteCommand;

    public DeleteCommand(String[] deleteCommand) {
        this.deleteCommand = deleteCommand;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        int taskNumberToDelete = Integer.parseInt(deleteCommand[1]);
        Task taskToDelete = taskList.get(taskNumberToDelete - 1);
        taskList.delete(taskNumberToDelete);

        try {
            storage.TaskListToFile(taskList);
        } catch (IOException e) {
            Ui.printExceptionMessage(e);
        }

        Ui.indicateRemovedTask(taskToDelete, taskList);
    }
}
