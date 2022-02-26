package butler.command;

import butler.ButlerInputException;
import butler.TaskList;
import butler.util.Storage;
import butler.util.ui.Ui;

public class TodoCommand extends Command {

    /** Description of what needs to be done for the task */
    private String description;

    /**
     * Constructs a TodoCommand object that executes a specified command.
     *
     * @param description description of the task.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes command behavior.
     * In this case it adds ToDo to the taskList and stores the updated list in a file.
     * Then it prints a success message to output, else it prints a failure message.
     *
     * @param taskList A managed list of tasks.
     * @param storage A handler class that handles file I/O, storing taskList details in a file.
     * @param ui A handler class that handles user input and output.
     * @throws ButlerInputException Throws the ButlerInputException thrown by sub-methods.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws ButlerInputException {
        String message = taskList.addToDoToList(description);
        storage.writeTaskListToFile(taskList);
        ui.setMessage(message);
    }
}
