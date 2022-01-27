package duke.command;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDateTime;

/**
 * Responsible for the functionality needed when creating a Deadline task.
 */
public class CreateDeadlineCommand extends Command{

    /**
     * Deadline task created.
     */
    private Deadline deadline;

    /**
     * Constructor to create CreateDeadlineCommand.
     * Creates a Deadline task.
     *
     * @param description string describing the task.
     * @param by the due Date/Time for deadline.
     */
    public CreateDeadlineCommand(String description, LocalDateTime by) {
        super(CommandType.CREATE_DEADLINE);
        deadline = new Deadline(description, by);
    }

    /**
     * Adds the Deadline task to task list, saves task list to file and
     * print out the response message via user interface.
     *
     * @param taskList list of tasks.
     * @param ui user interface of the chat bot.
     * @param storage storage used by chat bot.
     * @throw DukeException if there is a File I/O exception thrown when saving file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.add(deadline);
        storage.saveToFile(taskList);
        ui.showMessage("Got it! I've added this Deadline task:\n " + deadline + "\n"
                + taskList.sizeDescription());
    }
}
