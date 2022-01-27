package tasks;

import exceptions.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    public static String WRONG_FORMAT_ERROR_STRING = "Format for events: 'event [some event] /at [dd/mm/yyyy-hh:mm]'";
    DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm");
    DateTimeFormatter PRETTY_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public Task.TaskType taskType = Task.TaskType.EVENT;
    public String taskName;
    public LocalDateTime eventTime;

    public Event(String taskName, String eventTime) throws DukeException {
        this.taskName = taskName;
        try {
            this.eventTime = LocalDateTime.parse(eventTime, INPUT_FORMAT);
        } catch (DateTimeParseException err) {
            throw new DukeException(WRONG_FORMAT_ERROR_STRING);
        }
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)",
                this.isDone() ? "X" : " ", this.taskName, this.eventTime.format(PRETTY_FORMAT));
    }

    public String exportToString() {
        return String.format("%s %s %s %s",
            this.taskType, this.taskName, this.isDone(), this.eventTime.format(INPUT_FORMAT));
    }
}