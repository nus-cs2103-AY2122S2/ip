package storage;

import tasks.Task;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;

import storage.TaskFormatterException.UnsupportedTaskEncodingException;
import storage.TaskFormatterException.IllegalDecodingInputException;

import java.time.LocalDate;

import java.util.List;
import java.util.ArrayList;

public class TaskFormatter {
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
