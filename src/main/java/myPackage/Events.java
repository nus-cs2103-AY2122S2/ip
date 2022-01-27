package myPackage;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Events extends Task{
    private String date;
    private LocalDate localDate;
    public Events(String description, String date) {
        super(description);
        try {
            String[] str = date.split("by ");
            localDate = LocalDate.parse(str[1]);
            this.date =  localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
        catch (Exception e) {
            System.out.println("not proper date");
            this.date = date;
        }
        System.out.printf("[E][ ] %s (%s)", this.description, this.date);
    }
    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.printf("[E][%s] %s%n", this.getStatusIcon(), this.description);
    }

    public void unmarkAsDone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.printf("[E][%s] %s%n", this.getStatusIcon(), this.description);
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

    public String getOriginalDescription() {return description;}

    public String getTiming() {
        return this.date;
    }
}
