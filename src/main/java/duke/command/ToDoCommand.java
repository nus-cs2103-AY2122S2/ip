package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

/**
 * Represents the command to add a to-do item.
 */
public class ToDoCommand extends Command {
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
     * @throws DukeException if there is a problem updating the storage or user interface.
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
