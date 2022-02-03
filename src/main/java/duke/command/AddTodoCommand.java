package duke.command;

import java.io.IOException;
import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

import duke.task.Task;
import duke.task.Todo;

/**
 * A representation of the command for adding a Todo.
 */
public class AddTodoCommand extends Command {
    String commandArgument;

    /**
     * Class constructor.
     *
     * @param commandArgument command argument from user input
     */
    public AddTodoCommand(String commandArgument) {
        this.commandArgument = commandArgument;
    }

    /**
     * Executes adding todo command.
     * @param tasks TaskList class
     * @param ui Ui class
     * @param storage Storage class
     * @throws IOException if file not found
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task currentTask = new Todo(commandArgument);
        tasks.addTask(currentTask);

        storage.writeTaskToFile(tasks);
        return ui.printConfirmAdd(currentTask, tasks.getNumberOfTasks());
    }
}
