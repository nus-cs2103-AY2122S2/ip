package duke.commands;

import duke.exceptions.DukeException;
import duke.tasklist.TaskList;
import duke.ui.Ui;


public class AddDeadlineCommand extends Command {

    private String userInput;
    /**
     * Add a deadline task to task list
     *
     * @param userInput the input from user
     */
    public AddDeadlineCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public String execute(TaskList taskList, Ui ui) {
        try {
            boolean taskAddedSuccess = taskList.addDeadlineTask(userInput);
            if (taskAddedSuccess) {
                return Ui.printAddSuccess(taskList);
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
        return null;
    }
}
