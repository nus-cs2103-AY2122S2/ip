package duke.commands;

import duke.exceptions.DukeException;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

public class RemoveCommand extends Command {

    private String userInput;
    private String currTask;
    /**
     * Removes a task from task list
     *
     * @param userInput the input from user
     */
    public RemoveCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public String execute(TaskList taskList, Ui ui) throws DukeException {
        try {
            int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
            Task removedTask = taskList.getTasks().get(index);
            taskList.getTasks().remove(index);
            currTask = removedTask.toString();
            return Ui.printRemoveTask(currTask);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please enter index of task to be removed");
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("Please enter index within range 1 to " + taskList.getTasks().size());
        }
    }
}
