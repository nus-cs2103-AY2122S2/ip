package duke.command;

import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

import duke.task.Task;
import duke.task.Todo;

public class AddTodoCommand extends Command {
    String commandArgument;
    public AddTodoCommand(String commandArgument) {
        this.commandArgument = commandArgument;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        Task currentTask = new Todo(commandArgument);
        tasks.addTask(currentTask);

        ui.printConfirmAdd(currentTask, tasks);
        storage.writeTaskToFile(tasks);
    }
}
