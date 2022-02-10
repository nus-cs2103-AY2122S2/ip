package seedu.commands;

import java.time.LocalDateTime;

import seedu.duke.DukeException;
import seedu.storage.TaskList;
import seedu.task.Event;

public class EventCommand extends Command {

    private String description;
    private LocalDateTime dateTime;

    @Override
    public void input(String inst) throws DukeException {
        checkExist(inst);
        String[] task = inst.trim().split("/at", 2);

        if (task.length == 1) {
            throw new DukeException("Delimiter /at no found.");
        } else {
            assert task.length == 2: "Array is supposed to contain 2 items";
            description = task[0].trim();
            dateTime = checkDateTime(task[1].trim());
        }
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        Event t = new Event(description, dateTime);
        tasks.add(t);
        return "New event:" + t.toString() + "added!";
    }
}
