import java.time.LocalDate;
public class Event extends Task {

    Event(String description, String time) {
        super(description, LocalDate.parse(time));
    }

    Event(String description, boolean isDone, String time) {
        super(description, isDone, LocalDate.parse(time));
    }

    @Override
    public String toString() {
        String tempStr = " ";

        if (this.isDone) {
            tempStr = "X";
        }

        return "[E][" + tempStr + "] " + this.description + "(" + this.time.get().format(dateFormatter) + ")";
    }
}

