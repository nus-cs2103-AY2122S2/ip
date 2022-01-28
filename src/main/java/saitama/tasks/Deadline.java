package saitama.tasks;

import saitama.exceptions.InvalidFormatException;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDate deadline;

    public Deadline(String description, String by) throws InvalidFormatException {
        super(description);
        String[] time = by.split("/");
        if (time.length < 3) {
            throw new InvalidFormatException("Please enter a valid deadline format! (dd/mm/yyyy)");
        }
        try {
            LocalDate deadline = LocalDate.parse(time[2] + "-" + time[1] + "-" + time[0]);
            this.deadline = deadline;
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException("Please enter a valid deadline format! (dd/mm/yyyy)");
        }
    }

    public Deadline(String description, String by, boolean isDone) throws InvalidFormatException {
        super(description, isDone);
        String[] time = by.split("/");
        if (time.length < 3) {
            throw new InvalidFormatException("Please enter a valid deadline format! (dd/mm/yyyy)");
        }
        try {
            LocalDate deadline = LocalDate.parse(time[2] + "-" + time[1] + "-" + time[0]);
            this.deadline = deadline;
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException("Please enter a valid deadline format! (dd/mm/yyyy)");
        }
    }

    public void saveTask(FileWriter fw) throws IOException {
        String isDone = this.getStatusIcon() == "X" ? "1" : "0";
        fw.write("D " + isDone + " " + this.description + " /by " + this.deadline.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n");
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
