package seedu.commands;

import java.time.LocalDateTime;

import seedu.duke.DukeException;
import seedu.storage.TaskList;
import seedu.task.Event;

/**
 * The Event Command
 */
public class EventCommand extends Command {

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
        String[] task = inst.trim().split("/at", 2);

        if (task.length == 1) {
            throw new DukeException("Delimiter /at no found.");
        } else {
            assert task.length == 2 : "Array is supposed to contain 2 items";
            description = task[0].trim();
            dateTime = checkDateTime(task[1].trim());
        }
    }

    /**
     * Adds event task into list
     *
     * @param tasks The task list
     * @return The newly created event task
     */
    @Override
    public String execute(TaskList tasks) throws DukeException {
        Event task = new Event(description, dateTime);
        tasks.add(task);
        return super.show("New Event:", task);
    }
}
