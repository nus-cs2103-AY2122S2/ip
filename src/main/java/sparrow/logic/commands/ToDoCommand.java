package sparrow.logic.commands;

import sparrow.logic.task.ToDo;
import sparrow.model.TaskList;
import sparrow.storage.Storage;
import sparrow.ui.Ui;


/**
 * Represents the command to add a to-do item.
 */
public class ToDoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    private final ToDo toDo;

    public ToDoCommand(String description) {
        toDo = new ToDo(description);
    }

    /**
     * Executes the command.
     *
     * @param storage  Storage of Duke.
     * @param tasks Task list of Duke.
     * @param ui User interface of Duke.
     */
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.add(toDo);
        return ui.addTask(toDo) + ui.countTasks(tasks);
    }

    /**
     * Indicates whether the program should stop after this command.
     *
     * @return Boolean indicating whether the program should stop after this command.
     */
    public boolean isExit() {
        return false;
    }
}
