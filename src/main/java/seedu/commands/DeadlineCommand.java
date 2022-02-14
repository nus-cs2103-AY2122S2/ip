package seedu.commands;

import java.time.LocalDateTime;

import seedu.duke.DukeException;
import seedu.storage.TaskList;
import seedu.task.Deadline;

public class DeadlineCommand extends Command {

    private String description;
    private LocalDateTime dateTime;

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


    @Override
    public String execute(TaskList tasks) {
        Deadline task = new Deadline(description, dateTime);
        tasks.add(task);
        return super.print("New Deadline:", task);
    }
}
