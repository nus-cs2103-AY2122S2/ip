package model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import util.InputParser;

public class DeadlineTask extends Task {
    private final LocalDate deadlineTime;

    public DeadlineTask(String task, String deadlineTime, boolean isComplete) throws DateTimeParseException {
        super(task, isComplete);
        this.deadlineTime = LocalDate.parse(deadlineTime);
    }

    public DeadlineTask(String task, String deadlineTime) {
        this(task, deadlineTime, false);
    }

    public static DeadlineTask of(String taskBody) throws IncorrectTaskFormatException {
        String[] taskParameters = InputParser.parseTaskBody(taskBody);
        if (taskParameters.length != 3) {
            throw(new IncorrectTaskFormatException(String.format(ERROR_MESSAGE, taskBody)));
        } else if (!taskParameters[1].equals("by")) {
            throw(new IncorrectTaskFormatException(String.format(ERROR_MESSAGE, taskParameters[1])));
        }
        return new DeadlineTask(taskParameters[0], taskParameters[2]);
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + deadlineTime + ")";
    }

    @Override
    public String getType() {
        return "Deadline";
    }

    @Override
    public String getTaskBody() {
        return super.getTaskBody() + " | " + deadlineTime;
    }
}
