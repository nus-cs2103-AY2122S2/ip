import java.time.LocalDate;

public class DeadlineCommand extends AddCommand {
    public static final String COMMAND_WORD = "deadline";

    public DeadlineCommand(String taskDesc, String timeDate, LocalDate date) {
        this.task = new DeadlineTask(taskDesc, timeDate, date);
    }
}
