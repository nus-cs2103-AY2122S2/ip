package Tasks.TaskList;

import SparkExceptions.FileExceptions.TaskDecodingException;
import Tasks.TaskTypes.Deadline;
import Tasks.TaskTypes.Event;
import Tasks.TaskTypes.Task;
import Tasks.TaskTypes.ToDo;

/**
 * Contains methods to decode Tasks saved in a TaskFile
 */
class TaskDecoder {
    static Task decodeTask(String encodedTask) throws TaskDecodingException {
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
        return new ToDo(parseCompletionStatus(tokens[1]), tokens[2]);
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
