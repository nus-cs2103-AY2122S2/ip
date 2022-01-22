import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventTask extends Task {
    protected String time;
    protected LocalDate nowTime;


    public EventTask(String message) throws EmptyMessageException, DateFormatException, DateTimeParseException {
        String[] taskArray = message.split("/at");
        LocalDate nowTime;

        if (taskArray[0].equals("")) {
            throw new EmptyMessageException();
        } else if (taskArray.length == 1) {
            throw new DateFormatException("Sorry Master, when is the due? (include /at)");
        } else {
            nowTime = LocalDate.parse(taskArray[1].stripLeading().stripTrailing());
        }

        super.taskMessage = taskArray[0].stripLeading().stripTrailing();
        this.nowTime = nowTime;
        time = nowTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    public EventTask(String message, String when) {
        super(message);
        time = when;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }

    @Override
    public String textToFile() {
        return "E "+ super.textToFile() + " /at " + nowTime.toString();
    }
}
