package Duke.tasks;
import java.util.Date;

public class Deadline extends Task{
    Date date;

    public Deadline(String item, Date date) {
        super(item);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("D | %s | %s | %s", this.getStatusIcon(), super.toString(),
                this.date);
    }
}
