package duke.command;

import java.io.IOException;

import java.time.format.DateTimeParseException;

import static duke.constant.TaskConstant.PREFIX_BY_DEADLINE;

import duke.component.Storage;
import duke.component.Ui;
import duke.component.TaskList;

import duke.exception.DukeException;
import duke.exception.MissingDateTimeException;

import duke.task.Deadline;
import duke.task.Task;

public class AddDeadlineCommand extends Command {
    String commandArgument;
    public AddDeadlineCommand(String commandArgument) {
        this.commandArgument = commandArgument;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException, DateTimeParseException {
        String[] deadlineDetail = commandArgument.split(PREFIX_BY_DEADLINE, 2);
        if (deadlineDetail.length != 2) {
            throw new MissingDateTimeException();
        }

            Task currentTask = new Deadline(deadlineDetail[0], deadlineDetail[1]);
            tasks.addTask(currentTask);

            ui.printConfirmAdd(currentTask, tasks);
            storage.writeTaskToFile(tasks);

    }

    }

