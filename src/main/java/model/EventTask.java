package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import util.InputParser;

public class EventTask extends Task{
    private final LocalDateTime eventTime;

    //todo handle DateTimeParseException
    public EventTask(String task, String eventTime, boolean isComplete) throws DateTimeParseException {
        super(task, isComplete);
        this.eventTime = LocalDateTime.parse(eventTime);
    }

    public EventTask(String task, String eventTime) throws DateTimeParseException {
        this(task, eventTime, false);
    }

    public static EventTask of(String taskBody) throws IncorrectTaskFormatException {
        String[] taskParameters = InputParser.parseTaskBody(taskBody);
        if (taskParameters.length != 3) {
            throw(new IncorrectTaskFormatException(String.format(ERROR_MESSAGE, taskBody)));
        } else if (!taskParameters[1].equals("at")) {
            throw(new IncorrectTaskFormatException(String.format(ERROR_MESSAGE, taskParameters[1])));
        }
        return new EventTask(taskParameters[0], taskParameters[2]);
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + eventTime + ")";
    }

    @Override
    public String getType() {
        return "Event";
    }

    @Override
    public String getTaskBody() {
        return super.getTaskBody() + " | " + eventTime;
    }
}
