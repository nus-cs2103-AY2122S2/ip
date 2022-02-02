package luca.command;

import java.time.LocalDateTime;

import luca.common.DukeException;
import luca.storage.Storage;
import luca.task.Deadline;
import luca.task.TaskList;

/**
 * Responsible for the functionality needed when creating a Deadline task.
 */
public class CreateDeadlineCommand extends Command {

    /** Deadline task created. */
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
     * returns the response message.
     *
     * @param taskList list of tasks.
     * @param storage storage used by chat bot.
     * @return string output of the execution.
     * @throw DukeException if there is a File I/O exception thrown when saving file.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        taskList.add(deadline);
        storage.saveToFile(taskList);
        return ("Got it! I've added this Deadline task:\n " + deadline + "\n"
                + taskList.sizeDescription());
    }
}
