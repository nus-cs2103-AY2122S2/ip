package duke.commands;

import duke.exceptions.DukeException;
import duke.tasklist.TaskList;
import duke.ui.Ui;


public class AddEventCommand extends Command {

    private String userInput;

    /**
     * Add a event task to task list
     *
     * @param userInput the input from user
     */
    public AddEventCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public String execute(TaskList taskList, Ui ui) throws DukeException {
        try {
            boolean taskAddedSuccess = taskList.addEventTask(userInput);
            if (taskAddedSuccess) {
                return Ui.printAddSuccess(taskList);
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
        return null;
    }
}
