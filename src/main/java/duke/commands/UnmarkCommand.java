package duke.commands;

import duke.exceptions.DukeException;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

public class UnmarkCommand extends Command {

    private String userInput;
    private Task currTask;
    /**
     * Mark a task as incomplete
     *
     * @param userInput the input from user
     */
    public UnmarkCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public String execute(TaskList taskList, Ui ui) throws DukeException {
        try {
            int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
            assert index >= 1 : "Index less than 1!";
            Task task = taskList.getTasks().get(index);
            task.setIncomplete();
            currTask = task;
            return Ui.printUnmarkSuccess(currTask);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please enter a number of the item in the list you wish to unmark!");
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("Please enter index within range 1 to " + taskList.getTasks().size());
        }
    }
}
