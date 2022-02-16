package seedu.commands;

import java.time.LocalDateTime;

import seedu.duke.DukeException;
import seedu.storage.TaskList;
import seedu.task.Deadline;

/**
 * The Deadline Command
 */
public class DeadlineCommand extends Command {

    private String description;
    private LocalDateTime dateTime;

    /**
     * Checks if the string follows the given format
     *
     * @param inst The command the user entered
     * @throws DukeException The command does not follow the format
     */
    @Override
    public void validate(String inst) throws DukeException {
        checkExist(inst);
        String[] task = inst.trim().split("/by", 2);

        if (task.length == 1) {
            throw new DukeException("Delimiter /by no found.");
        } else {
            assert task.length == 2: "Array is supposed to contain 2 items";
            description = task[0].trim();
            dateTime = checkDateTime(task[1].trim());
        }
    }


    /**
     * Adds deadline task into list
     *
     * @param tasks The task list
     * @return The newly created deadline task
     */
    @Override
    public String execute(TaskList tasks) {
        Deadline task = new Deadline(description, dateTime);
        tasks.add(task);
        return super.show("New Deadline:", task);
    }
}
