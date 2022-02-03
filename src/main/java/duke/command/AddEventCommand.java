package duke.command;

import java.io.IOException;

import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

import duke.exception.DukeException;
import duke.exception.MissingDateTimeException;

import duke.task.Event;
import duke.task.Task;

import static duke.constant.TaskConstant.PREFIX_AT_EVENT;

/**
 * A representation of the command for adding an Event.
 */
public class AddEventCommand extends Command {
    String commandArgument;

    /**
     * Class constructor.
     *
     * @param commandArgument command argument from user input
     */
    public AddEventCommand(String commandArgument) {
        this.commandArgument = commandArgument;
    }

    /**
     * Execute adding event command.
     *
     * @param tasks TaskList class
     * @param ui Ui class
     * @param storage Storage class
     * @throws DukeException if missing datetime
     * @throws IOException if file not found
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        String[] eventDetail = commandArgument.split(PREFIX_AT_EVENT, 2);
        if (eventDetail.length != 2) {
            throw new MissingDateTimeException();
        }
        Task currentTask = new Event(eventDetail[0], eventDetail[1]);
        tasks.addTask(currentTask);
        storage.writeTaskToFile(tasks);
        return ui.printConfirmAdd(currentTask, tasks.getNumberOfTasks());
    }
}
