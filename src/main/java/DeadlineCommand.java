import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command implements DateValidator {
    private final String description;
    private LocalDate deadline;

    public DeadlineCommand(String description, String deadline) {
        try {
            this.description = description;
            this.deadline = validDate(deadline);
        } catch (DateTimeParseException e) {
            String exceptionMsg = "Please input date in a valid date-time format.";
            throw new DateTimeParseException(exceptionMsg, description.split(" /by ")[1], e.getErrorIndex());
        }
    }

    public LocalDate validDate(String dateStr) throws DateTimeParseException {
        return LocalDate.parse(dateStr);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList<Task> tasks, Ui ui, Storage storage) {
        Deadline task = new Deadline(tasks.size() + 1, this.description, this.deadline);
        tasks.add(task);
        ui.showMessage("Got it. I've added the deadline task:");
        ui.showMessage(task.toString());
        ui.showMessage("Now you have " + tasks.size() + " tasks in your list.");
    }
}
