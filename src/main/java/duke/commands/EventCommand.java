package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class EventCommand extends Command implements DateValidator {
    private final String description;
    private LocalDate date;

    public EventCommand(String description, String date) {
        try {
            this.description = description;
            this.date = validDate(date);
        } catch (DateTimeParseException e) {
            String exceptionMsg = "Please input date in a valid date-time format.";
            throw new DateTimeParseException(exceptionMsg, description.split(" /by ")[1], e.getErrorIndex());
        }
    }

    public LocalDate validDate(String dateStr) throws DateTimeParseException {
        return LocalDate.parse(dateStr);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList<Task> tasks, Ui ui, Storage storage) {
        Event task = new Event(tasks.size() + 1, this.description, this.date);
        tasks.add(task);
        ui.showMessage("Got it. I've added the deadline task:");
        ui.showMessage(task.toString());
        ui.showMessage("Now you have " + tasks.size() + " tasks in your list.");
    }
}
