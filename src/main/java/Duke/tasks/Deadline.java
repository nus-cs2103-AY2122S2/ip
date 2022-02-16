package Duke.tasks;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task{
    Date date;

    public Deadline(String item, Date date) {
        super(item);
        this.date = date;
    }
    @Override
    public Date getDate() {
        return this.date;
    }
    String convertDate() {
        DateFormat dateFormat = new SimpleDateFormat("EEE dd/MM/yyyy HHmm");
        String date = dateFormat.format(this.date);
        return  date;
    }
    @Override
    public String toString() {
        return String.format("D | %s | %s | %s", this.getStatusIcon(), super.toString(),
                convertDate());
    }
}
