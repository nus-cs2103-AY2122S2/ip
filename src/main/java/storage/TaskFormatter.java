package storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import storage.TaskFormatterException.IllegalDecodingInputException;
import storage.TaskFormatterException.UnsupportedTaskEncodingException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
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
        final String statusAndDesc = TaskFormatter
                .joinWithDivider(task.getIsDone() ? "1" : "0", task.getDescription());

        if (task instanceof Todo) {
            return TaskFormatter.joinWithDivider("T", statusAndDesc);
        }

        if (task instanceof Deadline) {
            final Deadline d = (Deadline) task;
            return TaskFormatter
                    .joinWithDivider("D", statusAndDesc, d.getBy().format(Deadline.DATE_INPUT_FORMAT));
        }

        if (task instanceof Event) {
            final Event e = (Event) task;
            return TaskFormatter.joinWithDivider("E", statusAndDesc, e.getAt());
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
        if (tokens.length < 3 || (!tokens[1].trim().equals("0") && !tokens[1].trim().equals("1"))) {
            throw new IllegalDecodingInputException();
        }

        Task task;
        switch (tokens[0].trim()) {
        case "T":
            task = new Todo(tokens[2].trim());
            break;
        case "D":
            task = new Deadline(tokens[2].trim(), LocalDate.parse(tokens[3].trim(), Deadline.DATE_INPUT_FORMAT));
            break;
        case "E":
            task = new Event(tokens[2].trim(), tokens[3].trim());
            break;
        default:
            throw new IllegalDecodingInputException();
        }

        if (tokens[1].trim().equals("1")) {
            task.markAsDone();
        }

        return task;
    }
}
