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

public class AddEventCommand extends Command {
    String commandArgument;
    public AddEventCommand(String commandArgument) {
        this.commandArgument = commandArgument;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        String[] eventDetail = commandArgument.split(PREFIX_AT_EVENT, 2);
        if (eventDetail.length != 2) {
            throw new MissingDateTimeException();
        }
        Task currentTask = new Event(eventDetail[0], eventDetail[1]);
        tasks.addTask(currentTask);
        ui.printConfirmAdd(currentTask, tasks);
        storage.writeTaskToFile(tasks);
    }
}
