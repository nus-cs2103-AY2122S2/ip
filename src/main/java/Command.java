import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public abstract class Command {
    public abstract CommandFeedback execute(TaskList taskList) throws InvalidArgumentException;
}
