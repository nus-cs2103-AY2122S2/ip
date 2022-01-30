import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

public class Event extends Task{
    public LocalDate date;
    public LocalTime time;

    public Event(String title, String deadline) {
        super(title);
        try {
            String[] deadlineList = deadline.split(" ");
            this.date = LocalDate.parse(deadlineList[0].replace("/", "-"));
            this.time = LocalTime.parse(deadlineList[1]);
        } catch (NullPointerException e){
            throw new IndexOutOfBoundsException();
        }
    }

    public String toString(){
        int day = date.getDayOfMonth();
        Month month = date.getMonth();
        int year = date.getYear();
        if (this.checked) {
            return String.format("[D][X] %s (at: %d %s %d %s)", title, day, month, year, time);
        } else {
            return String.format("[D][ ] %s (at: %d %s %d %s)", title, day, month, year, time);
        }
    }
}
