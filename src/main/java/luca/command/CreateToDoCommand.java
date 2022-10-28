package luca.command;

import luca.common.DukeException;
import luca.storage.Storage;
import luca.task.TaskList;
import luca.task.ToDo;

/**
 * Responsible for the functionality needed when creating a ToDoo task.
 */
public class CreateToDoCommand extends Command {

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
     * returns the response message.
     *
     * @param taskList list of tasks.
     * @param storage storage used by chat bot.
     * @return response string.
     * @throw DukeException if there is a File I/O exception thrown when saving file.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        taskList.add(toDo);
        storage.saveToFile(taskList);
        return "Got it! I've added this ToDo task:\n " + toDo + "\n"
                + taskList.sizeDescription();
    }
}
