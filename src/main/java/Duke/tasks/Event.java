package Duke.tasks;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Event extends Task{
    LocalDateTime date;

    public Event(String item, LocalDateTime date) {
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
        return String.format("E | %s | %s | %s", this.getStatusIcon(), super.toString(),
            convertDate());
    }
}
