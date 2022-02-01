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

    public void addToDo(String description) {
        tasks.add(new ToDo(description));
        saveData();
    }

    public void addDeadline(String description, String dateTime) {
        tasks.add(new Deadline(description, parseDateTime(dateTime)));
        saveData();
    }

    public void addEvent(String description, String dateTime, String duration) {
        tasks.add(new Event(description, parseDateTime(dateTime), parseDuration(duration)));
        saveData();
    }

    public void markTask(int index) {
        tasks.mark(index);
        saveData();
    }

    public void unmarkTask(int index) {
        tasks.unmark(index);
        saveData();
    }

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
