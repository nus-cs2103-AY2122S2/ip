package tasks;

import exceptions.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/** A class that functions as an abstraction of an event task. */
public class Event extends Task {

    public static String WRONG_FORMAT_ERROR_STRING = "Format for events: 'event [some event] /at [dd/mm/yyyy-hh:mm]'";
    DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm");
    DateTimeFormatter PRETTY_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public Task.TaskType taskType = Task.TaskType.EVENT;
    public String taskName;
    public LocalDateTime eventTime;

    /**
     * Constructor method for the Event task.
     *
     * @param taskName Name of the Event task.
     * @param eventTime The date and time of the Event.
     * @throws DukeException If the date and time argument is not formatted properly.
     */
    public Event(String taskName, String eventTime) throws DukeException {
        this.taskName = taskName;
        try {
            this.eventTime = LocalDateTime.parse(eventTime, INPUT_FORMAT);
        } catch (DateTimeParseException err) {
            throw new DukeException(WRONG_FORMAT_ERROR_STRING);
        }
    }

    /**
     * The string representation of the task to be displayed to the user.
     *
     * @return A string representation of the task for the user.
     */
    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)",
                this.isDone() ? "X" : " ", this.taskName, this.eventTime.format(PRETTY_FORMAT));
    }

    /**
     * Converts the task into a string representation to be used to be saved on disk.
     *
     * @return The task in string format to be used to be saved on disk.
     */
    public String exportToString() {
        return String.format("%s %s %s %s",
            this.taskType, this.taskName, this.isDone(), this.eventTime.format(INPUT_FORMAT));
    }
}