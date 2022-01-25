import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDate dt;

    public Deadline(String name, String deadline) throws DateTimeParseException{
        super(name);
        this.dt = LocalDate.parse(deadline.trim().replace("/","-"));
    }

    @Override
    public String toString(){
        return "[D] " + super.toString() + " (by: "
                + dt.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

