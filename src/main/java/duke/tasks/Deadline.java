package duke.tasks;
import java.time.*;
import java.time.format.*;

public class Deadline extends Task {
    private LocalDateTime deadline;

    //Constructor
    public Deadline (String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
        this.info = "D,0," + name + "," + deadline.getYear() + "," + deadline.getMonthValue() + ","
                + deadline.getDayOfMonth() + "," + deadline.getHour() + "," + deadline.getMinute();
    }

    //Accessor
    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    @Override
    public void mark() {
        this.isDone = true;
        this.info = "D,1," + name + "," + deadline.getYear() + "," + deadline.getMonthValue() + ","
                + deadline.getDayOfMonth() + "," + deadline.getHour() + "," + deadline.getMinute();
    }

    @Override
    public void unmark() {
        this.isDone = false;
        this.info = "D,0," + name + "," + deadline.getYear() + "," + deadline.getMonthValue() + ","
                + deadline.getDayOfMonth() + "," + deadline.getHour() + "," + deadline.getMinute();
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        if (isDone) {
            return "[D][X] " + this.name + "(By " + this.deadline.format(format) + ")";
        } else {
            return "[D][ ] " + this.name + "(By " + this.deadline.format(format) + ")";
        }
    }
}