package myPackage;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task{
    private String deadline;
    private LocalDate localDate;

    public Deadlines(String description, String deadline) {
        super(description);
        try {
            String[] str = deadline.split("by ");
            localDate = LocalDate.parse(str[1]);
            this.deadline = localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
        catch (Exception e) {
            System.out.println("not proper date");
            this.deadline = deadline;
        }
        System.out.printf("[D][ ] %s (%s)", this.description, this.deadline);
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.printf("[D][%s] %s%n", this.getStatusIcon(), this.description);
    }

    public void unmarkAsDone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.printf("[D][%s] %s%n", this.getStatusIcon(), this.description);
    }

    public String getTaskType() {
        return "D";
    }

    @Override
    public String getDescription() {
        return String.format("%s", this.description, deadline);
    }

    public String getOriginalDescription() {return description;}

    @Override
    public String getFullDescription() {
        return String.format("[D][ ] %s (%s)", this.description, deadline);
    }

    public String getTiming() {
        return this.deadline;
    }
}
