package duke.command;

import duke.manager.Ui;
import duke.manager.TaskList;
import duke.manager.Storage;
import duke.exception.DukeException;
import duke.task.ToDo;

/**
 * Represents a command that will add a ToDo Task to the TaskList upon execution.
 */
public class AddToDoCommand extends Command{
    private String task;

    /**
     * A constructor to store the name of the Task.
     *
     * @param task The name of the task.
     */
    public AddToDoCommand(String task) {
        super();
        this.task = task;
    }

    /**
     * Executes the command by adding a ToDo task into the TaskList.
     *
     * @param taskList A TaskList that stores the tasks.
     * @param ui An Ui object to handle user interaction.
     * @param storage A Storage object to handle saving of data.
     * @throws DukeException If there is an issue saving the tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ToDo todo = new ToDo(task);
        taskList.add(todo);
        ui.print("Got it. I've added this task:");
        ui.print(todo.toString());
        ui.print("Now you have " + taskList.numOfTasks() +  " tasks in the list.");
        try {
            storage.save(taskList);
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Returns true if it is an exit command and false otherwise.
     * @return a boolean.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}