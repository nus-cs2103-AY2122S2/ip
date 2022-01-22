package command;

import exception.DukeException;
import storage.Storage;
import task.Deadline;
import task.TaskList;
import task.Todo;
import ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class TodoCommand extends Command {
    String message;

    public TodoCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        try {
            taskList.add(new Todo(this.message, false));
        } catch (DateTimeParseException e) {
            throw new DukeException("Have you entered the date in yyyy-mm-dd format?");
        }
        storage.writeToFile(taskList);
        ui.outputMessage("Got it. I've added this task: \n" +
                taskList.get(taskList.size() -1) +
                "\nNow you have " + taskList.size() + " tasks in the list.");
    }
}
