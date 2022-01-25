import java.io.IOException;
import java.time.DateTimeException;
import java.time.format.DateTimeParseException;

public class AddDeadlineCommand extends Command {
    String commandArgument;
    public AddDeadlineCommand(String commandArgument) {
        this.commandArgument = commandArgument;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException, DateTimeParseException {
        String[] deadlineDetail = commandArgument.split(Constant.PREFIX_DEADLINE, 2);
        if (deadlineDetail.length != 2) {
            throw new MissingDateTimeException();
        }

            Task currentTask = new Deadline(deadlineDetail[0], deadlineDetail[1]);
            tasks.addTask(currentTask);

            ui.printConfirmAdd(currentTask, tasks);
            storage.writeTaskToFile(tasks);

    }

    }

