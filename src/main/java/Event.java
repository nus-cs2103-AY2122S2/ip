package tesseract.task;

import tesseract.main.Date;

import tesseract.task.Task;

import java.time.LocalDate;

public class Event extends Task {
    protected String at;
    protected Date date;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.date = new Date(at);
    }

    @Override
    public String toMemoryString() {
        return "E" + super.toMemoryString()
                + "@" + this.at;
    }

    @Override
    public boolean isOn(Date date) {
        return this.date.equals(date);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (at: " + date.formattedTime() + ")";
    }
}
