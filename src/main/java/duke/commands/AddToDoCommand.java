package duke.commands;

import duke.exceptions.DukeException;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class AddToDoCommand extends Command {

    private String userInput;

    /**
     * Add a todo task to task list
     *
     * @param userInput the input from user
     */
    public AddToDoCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public String execute(TaskList tasks, Ui ui) throws DukeException {
        try {
            boolean taskAddedSuccess = tasks.addToDoTask(userInput);
            System.out.println(taskAddedSuccess);
            if (taskAddedSuccess) {
                return Ui.printAddSuccess(tasks);
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
        return null;
    }
}
