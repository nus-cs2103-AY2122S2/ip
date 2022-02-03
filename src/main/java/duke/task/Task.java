package duke.task;

import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    private String name;
    private boolean done;
    private LocalDate date;
    private LocalTime time;
    private LocalTime timeFrom;
    private LocalTime timeTo;
    private String type;

    public Task(String type, String name) {
        this.type = type;
        this.name = name;
        this.done = false;
    }

    public Task(String type, String name, LocalDate date, LocalTime time) {
        this.type = type;
        this.name = name;
        this.date = date;
        this.time = time;
        this.done = false;
    }

    public Task(String type, String name, LocalDate date, LocalTime timeFrom, LocalTime timeTo) {
        this.type = type;
        this.name = name;
        this.date = date;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.done = false;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    public String getType() {
        return type;
    }

    public String getDone() {
        if (done) {
            return "1";
        } else {
            return "0";
        }
    }

    public String getName(){
        return name;
    }

    public String getDate() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return date.format(outputFormatter).toString();
    }

    public String getTime() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("HHmm");
        return time.format(outputFormatter).toString();
    }

    public String getTimeFrom() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("HHmm");
        return timeFrom.format(outputFormatter).toString();
    }

    public String getTimeTo() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("HHmm");
        return timeTo.format(outputFormatter).toString();
    }


    @Override
    public String toString() {
        String str = "[";
        //Completion
        if (this.done) {
            str += "X";
        } else {
            str += " ";
        }
        str += "] " + this.name;
        return str;
    }
}
