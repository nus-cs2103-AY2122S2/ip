package main.java.ari.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import main.java.ari.exception.CommandFormatException;
import main.java.ari.exception.EmptyCommandException;

public class DeadlineTask extends Task {
    protected String time;
    protected LocalDate nowTime;

    public DeadlineTask(String message) throws EmptyCommandException, CommandFormatException, DateTimeParseException {
        String[] taskArray = message.split("/by");

        LocalDate nowTime;
        if (taskArray[0].equals("")) {
            throw new EmptyCommandException();
        } else if (taskArray.length == 1) {
            throw new CommandFormatException();
        } else {
            nowTime = LocalDate.parse(taskArray[1].stripLeading().stripTrailing());
        }

        super.taskDescription = taskArray[0].stripLeading().stripTrailing();
        this.nowTime = nowTime;
        time = nowTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    public DeadlineTask(String message, String deadline, LocalDate date) {
        super.taskDescription = message;
        this.time = deadline;
        this.nowTime = date;
    }

    public DeadlineTask(String message, String deadline) {
        super(message);
        time = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + nowTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    @Override
    public String writeToFile() {
        return "deadline " + super.writeToFile() + "/by " + time.toString();
    }

}
