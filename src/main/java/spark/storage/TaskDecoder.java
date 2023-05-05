package spark.storage;

import spark.exceptions.fileexceptions.TaskDecodingException;
import spark.tasks.tasktypes.Deadline;
import spark.tasks.tasktypes.Event;
import spark.tasks.tasktypes.Task;
import spark.tasks.tasktypes.Todo;

/**
 * Contains methods to decode Tasks stored in the save-file on
 * the user's hard-disk.
 */
public class TaskDecoder {
    /**
     * Converts the encoded-representation of a Task to a Task object
     * and returns it.
     *
     * @param encodedTask            the encoded-representation of a Task.
     * @return                       the decoded Task object
     * @throws TaskDecodingException if the encoded-representation of a Task
     *                               could not be decoded to any known forms.
     */
    public static Task decodeTask(String encodedTask) throws TaskDecodingException {
        String[] tokens = encodedTask.split(" @@@ ");
        String taskType = tokens[0];

        Task t;

        switch (taskType) {
        case "T":
            t = decodeTodo(tokens);
            break;
        case "D":
            t = decodeDeadline(tokens);
            break;
        case "E":
            t = decodeEvent(tokens);
            break;
        default:
            throw new TaskDecodingException();
        }

        return t;
    }

    private static Task decodeTodo(String[] tokens) {
        return new Todo(parseCompletionStatus(tokens[1]), tokens[2]);
    }

    private static Task decodeDeadline(String[] tokens) {
        return new Deadline(parseCompletionStatus(tokens[1]), tokens[2], tokens[3]);
    }

    private static Task decodeEvent(String[] tokens) {
        return new Event(parseCompletionStatus(tokens[1]), tokens[2], tokens[3]);
    }

    private static boolean parseCompletionStatus(String isDone) {
        return isDone.equals("true");
    }
}
