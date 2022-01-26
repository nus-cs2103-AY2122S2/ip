import java.time.LocalDate;

public class Deadline extends Task {

    Deadline(String description, String deadline) {
        super(description, LocalDate.parse(deadline));
    }

    Deadline(String description, boolean isDone, String deadline) {
        super(description, isDone, LocalDate.parse(deadline));
    }

    @Override
    public String toString() {
        String tempStr = " ";

        if (this.isDone) {
            tempStr = "X";
        }

        return "[D][" + tempStr + "] " + this.description + "(" + this.time.get().format(dateFormatter) + ")";
    }

}
