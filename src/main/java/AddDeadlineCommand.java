import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AddDeadlineCommand extends Command{
    private String taskName;
    private String ddl;

    public AddDeadlineCommand(String taskName, String ddl) {
        this.taskName = taskName;
        this.ddl = ddl;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws PykeException, DateTimeParseException, IOException {
        if (taskName.isEmpty()) {
            throw new EmptyDescriptionException();
        } else if (ddl.isEmpty()) {
            throw new EmptyTimeException();
        } else {
            taskList.addTask(new Deadline(taskName, LocalDate.parse(ddl)));
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
