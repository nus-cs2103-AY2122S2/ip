import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task{
    protected Date by;

    public Deadline(String deadlineName, String by) throws DukeException {
        super(deadlineName);
        try {
            this.by = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(by);
        } catch (Exception e) {
            throw new DukeException("Please enter the date in \"dd / MM / yyyy HH:mm\" format");
        }
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy @ hh:mm a");
        return String.format("[D]%s (by: %s)", super.toString(), sdf.format(this.by));
    }

    public String write() {
        String markStatus = super.isMarked ? "1" : "0";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return String.format("%s | %s | %s | %s\n", "D", markStatus, super.taskName, sdf.format(this.by));
    }
}
