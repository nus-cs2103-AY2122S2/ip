package seedu.commands;

import seedu.duke.DukeException;
import seedu.storage.TaskList;
import seedu.task.Task;

/**
 * The Priority Command
 */
public class PriorityCommand extends Command {

    private int idx;
    private int priority;

    /**
     * Checks if the string follows the given format
     *
     * @param inst The command the user entered
     * @throws DukeException The command does not follow the format
     */
    @Override
    public void validate(String inst) throws DukeException {
        checkExist(inst);
        String[] arr = inst.split(" ", 2);
        
        if (arr.length < 2) {
            throw new DukeException("Either no index or no priority level");
        }

        idx = checkInt(arr[0]) - INDEX_OFFSET;
        priority = checkInt(arr[1]);
    }

    /**
     * Change the priority level of the task
     *
     * @param tasks The task list
     * @return The updated task
     * @throws DukeException Task index could not be found
     */
    @Override
    public String execute(TaskList tasks) throws DukeException {
        Task task = tasks.get(idx);
        assert task != null: "Need to have task before continuing";
        task.setPriority(priority);
        tasks.sort();
        return super.show("Priority Changed:", task);
    }
}
