import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{
    private String time;
    LocalDateTime datetime;


    Event(String description, String time) {
        super(description);
        this.time = time;
        try {
            datetime = LocalDateTime.parse(time);
        } catch (DateTimeParseException e) {
            datetime = null;
        }
    }

    public void print() {
        System.out.print("[E]");
        System.out.print("[" + (this.completed ? "x" : " ") +  "] " + this.description);
        if (datetime == null) {
            System.out.println(" (at: " + this.time + ")");
        } else {
            System.out.println(" (at: " + this.datetime.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a")) + ")");
        }

    }

    @Override
    public String[] getDetails() {
        String[] details = super.getDetails();
        details[0] = TaskType.EVENT.toString();
        details[3] = time;
        return details;
    }
}
