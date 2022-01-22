package command;

import exception.DukeException;
import storage.Storage;
import task.Event;
import task.TaskList;
import ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class EventCommand extends Command {
    String message;
    String time;

    public EventCommand(String message, String time) {
        this.message = message;
        this.time = time;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        try {
            taskList.add(new Event(this.message, false, LocalDate.parse(this.time)));
        } catch (DateTimeParseException e) {
            throw new DukeException("Have you entered the date in yyyy-mm-dd format?");
        }
        storage.writeToFile(taskList);
        ui.outputMessage("Got it. I've added this task: \n" +
                taskList.get(taskList.size() -1) +
                "\nNow you have " + taskList.size() + " tasks in the list.");
    }
}
