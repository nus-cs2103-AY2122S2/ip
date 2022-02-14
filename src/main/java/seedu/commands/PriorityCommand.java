package seedu.commands;

import seedu.duke.DukeException;
import seedu.storage.TaskList;
import seedu.task.Task;

/**
 *
 */
public class PriorityCommand extends Command {

    private int idx;
    private int priority;
    
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

    @Override
    public String execute(TaskList tasks) throws DukeException {
        Task task = tasks.get(idx);
        assert task != null: "Need to have task before continuing";
        task.setPriority(priority);
        tasks.sort();
        return super.print("Priority Changed:", task);
    }
}
