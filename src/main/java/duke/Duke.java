package duke;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Storage;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Encapsulates the main high-level logic of the bot.
 */
public class Duke {
    private final TaskList tasks;
    private final Storage storage = new Storage();

    /**
     * Constructs a Duke instance.
     */
    public Duke() {
        tasks = storage.loadTasks();
    }

    public TaskList getTasks() {
        return tasks;
    }

    public void saveData() {
        storage.saveTasks(tasks);
    }

    /**
     * Adds a ToDo to the list of tasks.
     *
     * @param description Description of the task.
     */
    public void addToDo(String description) {
        tasks.add(new ToDo(description));
        saveData();
    }

    /**
     * Adds a Deadline to the list of tasks.
     *
     * @param description Description of the task.
     * @param dateTime    Date and time of the deadline.
     */
    public void addDeadline(String description, String dateTime) {
        tasks.add(new Deadline(description, parseDateTime(dateTime)));
        saveData();
    }

    /**
     * Adds an Event to the list of tasks.
     *
     * @param description Description of the task.
     * @param dateTime    Date and time of the event.
     * @param duration    Duration of the event.
     */
    public void addEvent(String description, String dateTime, String duration) {
        tasks.add(new Event(description, parseDateTime(dateTime), parseDuration(duration)));
        saveData();
    }

    /**
     * Marks a task as done.
     *
     * @param index Index of the task to mark as done.
     */
    public void markTask(int index) {
        tasks.mark(index);
        saveData();
    }

    /**
     * Marks a taask as not done.
     *
     * @param index Index of the task to mark as not done.
     */
    public void unmarkTask(int index) {
        tasks.unmark(index);
        saveData();
    }

    /**
     * Deletes a task.
     *
     * @param index Index of the task to delete.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
        saveData();
    }

    private LocalDateTime parseDateTime(String dateTime) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("yyyy-M-d[ HHmm]")
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0).parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .toFormatter();

        try {
            return LocalDateTime.parse(dateTime, formatter);

        } catch (DateTimeParseException e) {
            throw new DukeException(dateTime + " is not a valid date. Example: 2022-3-15 1630");
        }
    }

    private Duration parseDuration(String duration) {
        try {
            return Duration.parse("PT" + duration);
        } catch (DateTimeParseException e) {
            throw new DukeException(duration + " is not a valid duration. Example: 1h5m");
        }
    }
}
