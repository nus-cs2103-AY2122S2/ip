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
    /**
     * Add Todo Task to TaskList.
     * Also overwrite Storage.
     *
     * @param ui Ui for outputting message.
     * @param storage Storage for rewritting TaskList.
     * @param taskList TaskList that stores Tasks.
     * @throws DukeException If problems with writing to Storage.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        taskList.add(new Todo(this.message, false));
        storage.writeToFile(taskList);
        ui.outputMessage("Got it. I've added this task: \n" +
                taskList.get(taskList.size() - 1) +
                "\nNow you have " + taskList.size() + " tasks in the list.");
    }
}
