import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AddEventCommand extends Command{
    private String taskName;
    private String eventTime;

    public AddEventCommand(String taskName, String eventTime) {
        this.taskName = taskName;
        this.eventTime = eventTime;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws PykeException, DateTimeParseException, IOException {
        if (taskName.isEmpty()) {
            throw new EmptyDescriptionException();
        } else if (eventTime.isEmpty()) {
            throw new EmptyTimeException();
        } else {
            taskList.addTask(new Event(taskName, LocalDate.parse(eventTime)));
            storage.saveFile(taskList);
            ui.outputText("Got it. I've added this task:\n    "
                    + taskList.getTaskOutputStyle(taskList.getSize() - 1)
                    + "\nNow you have " + taskList.getSize() + " tasks in the list.");
        }
    }
    public boolean isExit() {
        return false;
    }
}
