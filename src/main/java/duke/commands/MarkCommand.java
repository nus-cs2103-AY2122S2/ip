package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

public class MarkCommand extends Command {

    private String userInput;
    private Task currTask;

    /**
     * Mark a task as complete
     *
     * @param userInput the input from user
     */
    public MarkCommand(String userInput) throws DukeException {
        this.userInput = userInput;
    }

    @Override
    public String execute(TaskList taskList, Ui ui) throws DukeException {
        try {
            int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
            Task task = taskList.getTasks().get(index);
            task.setComplete();
            currTask = task;
            Storage.saveData(taskList);
            return Ui.printMarkSuccess(currTask);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please enter a number of the item in the list you wish to mark!");
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("Please enter index within range 1 to " + taskList.getTasks().size());
        }

    }

}
