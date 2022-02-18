package myPackage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Events extends Task {
    private String date;
    private LocalDate localDate;
    private boolean isDone;

    public Events(String description, String date) {
        super(description);
        this.isDone = false;
        try {
            String[] dateUnformatted = date.split("by ");
            String[] dateSplit = dateUnformatted[1].split("-");
            localDate = LocalDate.of(Integer.parseInt(dateSplit[0]), Integer.parseInt(dateSplit[1]), Integer.parseInt(dateSplit[2]));
            this.date = localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
        } catch (Exception e) {
            System.out.println("not proper date");
            this.date = date;
        }
        System.out.printf("[E][ ] %s (%s)", this.description, this.date);
    }

    public String markAsDone() {
        this.isDone = true;
        return String.format("Nice! I've marked this task as done:\n[E][X] %s%n", this.description);
    }

    public String unmarkAsDone() {
        this.isDone = false;
        return String.format("OK, I've marked this task as not done yet:\n[E][ ] %s%n", this.description);
    }

    public String getTaskType() {
        return "E";
    }

    @Override
    public String getDescription() {
        return String.format("%s (%s)", this.description, date);
    }

    @Override
    public String getFullDescription() {
        return String.format("[E][ ] %s (%s)", this.description, date);
    }

    public String getOriginalDescription() {
        return description;
    }

    public String getTiming() {
        return this.date;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }
}
