import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task {
    protected String time;
    protected LocalDate nowTime;

    public DeadlineTask(String message) throws EmptyMessageException, DateFormatException, DateTimeParseException {
        String[] taskArray = message.split("/by");

        LocalDate nowTime;
        if (taskArray[0].equals("")) {
            throw new EmptyMessageException();
        } else if (taskArray.length == 1) {
            throw new DateFormatException("Sorry Master, when is the due? (include /by)");
        } else {
            nowTime = LocalDate.parse(taskArray[1].stripLeading().stripTrailing());
        }

        super.taskMessage = taskArray[0].stripLeading().stripTrailing();
        this.nowTime = nowTime;
        time = nowTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    public DeadlineTask(String message, String deadline) {
        super(message);
        time = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time + ")";
    }

}
