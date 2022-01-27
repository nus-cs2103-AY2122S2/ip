package bernie.commands;

import bernie.enums.Type;
import bernie.exceptions.InvalidArgumentException;
import bernie.parser.Parser;
import bernie.storage.Storage;
import bernie.tasks.Task;
import bernie.tasks.TaskList;
import bernie.ui.UiHandler;

public class DeleteCommand extends Command {
    String[] parsedArr;

    public DeleteCommand(TaskList tasks, UiHandler uiHandler, Storage storage, Parser parser, String input) 
            throws InvalidArgumentException {
        super(tasks, uiHandler, storage, parser, input);
        this.parsedArr = parser.getParams(Type.DELETE, input);
    }

    /**
     * Delete the task according to the user input. The new state of the
     * tasks is saved to the data directory after deletion of task
     */
    public void execute() {
        String taskNum = parsedArr[0];
        Task deletedTask = tasks.deleteTask(taskNum);
        storage.saveTasks(tasks);
        uiHandler.showDeleteMsg(deletedTask, tasks.numTasksLeft());
    }
}
