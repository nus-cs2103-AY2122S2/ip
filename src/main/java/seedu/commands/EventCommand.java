package seedu.commands;

import seedu.duke.DukeException;
import seedu.duke.Ui;
import seedu.storage.Storage;
import seedu.storage.TaskList;
import seedu.task.Event;

import java.time.LocalDateTime;

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
            description = task[0].trim();
            dateTime = checkDateTime(task[1].trim());
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Event t = new Event(description, dateTime);
        tasks.add(t);
        ui.printDone(t, " added!");
    }
}
