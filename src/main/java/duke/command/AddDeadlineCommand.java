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

/**
 * A representation of the command for adding a Deadline.
 */
public class AddDeadlineCommand extends Command {
    String commandArgument;

    /**
     * Class constructor.
     *
     * @param commandArgument command argument from user input
     */
    public AddDeadlineCommand(String commandArgument) {
        this.commandArgument = commandArgument;
    }

    /**
     * Executes adding deadline command.
     *
     * @param tasks TaskList class
     * @param ui Ui class
     * @param storage Storage class
     * @throws DukeException if missing datetime
     * @throws IOException if file not found
     * @throws DateTimeParseException if wrong format datetime
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException, DateTimeParseException {
        String[] deadlineDetail = commandArgument.split(PREFIX_BY_DEADLINE, 2);
        if (deadlineDetail.length != 2) {
            throw new MissingDateTimeException();
        }

        Task currentTask = new Deadline(deadlineDetail[0], deadlineDetail[1]);
        tasks.addTask(currentTask);

        storage.writeTaskToFile(tasks);
        return ui.printConfirmAdd(currentTask, tasks.getNumberOfTasks());

    }

}

