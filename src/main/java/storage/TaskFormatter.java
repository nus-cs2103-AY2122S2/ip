package storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import storage.TaskFormatterException.IllegalDecodingInputException;
import storage.TaskFormatterException.UnsupportedTaskEncodingException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.TaskPriority;
import tasks.Todo;

/**
 * Represents a utility that serialize and deserialize a set of tasks
 * that is retrieved/persisted from/to the filesystem.
 */
public class TaskFormatter {
    /**
     * Serializes a set of tasks to the appropriate format for filesystem
     * storage.
     *
     * @param tasks the set of tasks to serialize.
     * @return A string representing the serialized set of tasks.
     * @throws TaskFormatterException If any of the given tasks is unsupported.
     */
    public static String encode(List<? extends Task> tasks) throws TaskFormatterException {
        String encoding = "";
        for (int i = 0; i < tasks.size(); i++) {
            if (i > 0) {
                encoding += "\n";
            }
            encoding += TaskFormatter.encodeTask(tasks.get(i));
        }
        return encoding;
    }

    private static String encodeTask(Task task) throws TaskFormatterException {
        final String status = task.getIsDone() ? "1" : "0";
        final String priority = task.getPriority().toString();
        final String statusPriorityDesc = TaskFormatter.joinWithDivider(status, priority, task.getDescription());

        if (task instanceof Todo) {
            return TaskFormatter.joinWithDivider("T", statusPriorityDesc);
        }

        if (task instanceof Deadline) {
            final Deadline d = (Deadline) task;
            final String dueDate = d.getBy().format(Deadline.DATE_INPUT_FORMAT);
            return TaskFormatter.joinWithDivider("D", statusPriorityDesc, dueDate);
        }

        if (task instanceof Event) {
            final Event e = (Event) task;
            return TaskFormatter.joinWithDivider("E", statusPriorityDesc, e.getAt());
        }

        throw new UnsupportedTaskEncodingException();
    }

    private static String joinWithDivider(String... values) {
        String result = "";
        for (int i = 0; i < values.length; i++) {
            if (i > 0) {
                result += " | ";
            }
            result += values[i];
        }
        return result;
    }

    /**
     * Deserializes a string representation of the tasks from the filesystem
     * to a list of Task objects.
     *
     * @param str the string representation of a serialized set of tasks.
     * @return A list of Task objects that are deserialized from the given serialized input.
     * @throws TaskFormatterException If the serialized input given is of an invalid format.
     */
    public static List<Task> decode(String str) throws TaskFormatterException {
        final List<Task> decodedTasks = new ArrayList<>();
        if (str.trim().isEmpty()) {
            return decodedTasks;
        }

        final String[] lines = str.split(System.lineSeparator());
        for (final String line : lines) {
            decodedTasks.add(TaskFormatter.decodeTask(line));
        }

        return decodedTasks;
    }

    private static Task decodeTask(String str) throws TaskFormatterException {
        final String[] tokens = str.split("\\|");
        if (tokens.length < 4) {
            throw new IllegalDecodingInputException();
        }

        final Task task = TaskFormatter.parseTaskType(tokens);

        final boolean isDone = TaskFormatter.parseCompletionStatus(tokens[1]);
        if (isDone) {
            task.markAsDone();
        }

        final TaskPriority p = TaskFormatter.parsePriorityType(tokens[2]);
        task.setPriority(p);

        return task;
    }

    private static Task parseTaskType(String[] tokens) throws TaskFormatterException {
        final String command = tokens[0].trim();
        final String description = tokens[3].trim();

        switch (command) {
        case "T":
            return new Todo(description);
        case "D":
            final LocalDate dueDate = LocalDate.parse(tokens[4].trim(), Deadline.DATE_INPUT_FORMAT);
            return new Deadline(description, dueDate);
        case "E":
            final String startsAt = tokens[4].trim();
            return new Event(description, startsAt);
        default:
            throw new IllegalDecodingInputException();
        }
    }

    private static final boolean parseCompletionStatus(String status) throws TaskFormatterException {
        if (status.trim().equals("0")) {
            return false;
        }
        if (status.trim().equals("1")) {
            return true;
        }
        throw new IllegalDecodingInputException();
    }

    private static final TaskPriority parsePriorityType(String priority) throws TaskFormatterException {
        try {
            return TaskPriority.parsePriority(priority.trim());
        } catch (IllegalArgumentException ex) {
            throw new IllegalDecodingInputException();
        }
    }
}
