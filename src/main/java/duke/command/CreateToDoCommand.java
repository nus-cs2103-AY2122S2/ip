package duke.command;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

/**
 * Responsible for the functionality needed when creating a ToDoo task.
 */
public class CreateToDoCommand extends Command{

    /** ToDo task created. */
    private ToDo toDo;

    /**
     * Constructor to create CreateToDoCommand.
     * Creates a ToDo task.
     *
     * @param description string describing the task.
     */
    public CreateToDoCommand(String description) {
        super(CommandType.CREATE_TODO);
        toDo = new ToDo(description);
    }

    /**
     * Adds the ToDo task to task list, saves task list to file and
     * print out the response message via user interface.
     *
     * @param taskList list of tasks.
     * @param ui user interface of the chat bot.
     * @param storage storage used by chat bot.
     * @throw DukeException if there is a File I/O exception thrown when saving file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.add(toDo);
        storage.saveToFile(taskList);
        ui.showMessage("Got it! I've added this ToDo task:\n " + toDo + "\n"
                + taskList.sizeDescription());
    }
}
