package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

public class Event extends Task{
    private final LocalDate date;
    private final LocalTime time;

    public Event(String title, LocalDate date, LocalTime time){
        super(title);
        this.date = date;
        this.time = time;
        System.out.println("added: " + this.toString());
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
