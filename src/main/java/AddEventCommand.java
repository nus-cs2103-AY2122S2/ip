import java.io.IOException;

public class AddEventCommand extends Command {
    String commandArgument;
    public AddEventCommand(String commandArgument) {
        this.commandArgument = commandArgument;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        String[] eventDetail = commandArgument.split(Constant.PREFIX_EVENT, 2);
        if (eventDetail.length != 2) {
            throw new MissingDateTimeException();
        }
        Task currentTask = new Event(eventDetail[0], eventDetail[1]);
        tasks.addTask(currentTask);
        ui.printConfirmAdd(currentTask, tasks);
        storage.writeTaskToFile(tasks);
    }
}
