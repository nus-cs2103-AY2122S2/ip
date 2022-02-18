package Duke.tasks;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    LocalDateTime date;

    public Deadline(String item, LocalDateTime date) {
        super(item);
        this.date = date;
    }
    @Override
    public LocalDateTime getDate() {
        return this.date;
    }

    String convertDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE dd/MM/yyyy HHmm");
        String date = this.date.format(formatter);
        return  date;
    }
    @Override
    public String toString() {
        return String.format("D | %s | %s | %s", this.getStatusIcon(), super.toString(),
                convertDate());
    }
}
