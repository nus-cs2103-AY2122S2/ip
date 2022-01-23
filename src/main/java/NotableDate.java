import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;

public class NotableDate {
    LocalDate localDate;
    ArrayList<Task> tasks;

    public NotableDate(LocalDate date) {
        this.localDate = date;
        tasks = new ArrayList<>();
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    @Override
    public String toString() {
        return localDate.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    }
}
