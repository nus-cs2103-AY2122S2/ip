package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;


public class AddDeadlineCommand extends Command {

    private String userInput;
    /**
     * Constructor for AddDeadlineCommand
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
                Storage.saveData(taskList);
                return Ui.printAddSuccess(taskList);
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
        return null;
    }
}
