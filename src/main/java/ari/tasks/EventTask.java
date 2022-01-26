package main.java.ari.tasks;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import main.java.ari.exception.CommandFormatException;
import main.java.ari.exception.EmptyCommandException;

public class EventTask extends Task {
    protected String time;
    protected LocalDate nowTime;

    public EventTask(String message, String eventTime, LocalDate date) {
        super.taskDescription = message;
        this.time = eventTime;
        this.nowTime = date;
    }

    public EventTask(String message) throws EmptyCommandException, CommandFormatException, DateTimeParseException {
        String[] taskArray = message.split("/at");
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

    public EventTask(String message, String when) {
        super(message);
        time = when;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + nowTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    @Override
    public String writeToFile() {
        return "event "+ super.writeToFile() + "/at " + nowTime.toString();
    }
}
