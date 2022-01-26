import java.time.LocalDate;

public class EventCommand extends AddCommand {
    public static final String COMMAND_WORD = "event";

    public EventCommand(String taskDesc, String timeDate, LocalDate date) {
        this.task = new EventTask(taskDesc, timeDate, date);
    }

}
