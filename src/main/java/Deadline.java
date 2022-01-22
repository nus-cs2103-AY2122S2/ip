import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    private final String DEADLINE_STRING;
    private final LocalDateTime deadLine;
    public Deadline(String description, String deadLine) throws DukeException{
        super(description);
        this.deadLine = this.parseDeadline(deadLine);
        this.DEADLINE_STRING = this.formatDeadline();
    }

    private LocalDateTime parseDeadline(String deadLine) throws DukeException{
        String[] temp = deadLine.split(" ",2);
        if(temp.length <= 1 || temp[1].length() < 4) {
            throw new DukeWrongInputFormatException("Format for deadline is wrong. Please refer to list of commands.");
        }
        try {
            return LocalDateTime.parse(temp[0] + "T" + temp[1].charAt(0) + temp[1].charAt(1)
                    + ":" + temp[1].charAt(2) + temp[1].charAt(3) + ":00");
        } catch (DateTimeParseException e) {
            throw new DukeWrongInputFormatException("Format for deadline is wrong. Please refer to list of commands.");
        }
    }

    private String formatDeadline() {
        DateTimeFormatter form = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return deadLine.format(form);
    }

    @Override
    public String formatSave() {
        return "D |" + (super.isDone ? "1| " : "0| ") + super.description + " /by " + deadLine;
    }

    /*
     * Customized toString method for Deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.DEADLINE_STRING + ")";
    }
}
