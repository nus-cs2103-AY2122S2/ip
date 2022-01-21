package Commands;

import Exceptions.EmptyDescriptionException;
import Exceptions.DukeException;

public class DeadlineCommand {
    public static DukeBot.Deadline preProcessing(String input, String[] parts) throws DukeException {
        if (parts.length == 1) {
            throw new EmptyDescriptionException("deadline");
        }
        String description = input.substring(9);
        String deadlineParts[] = description.split("/");
        String byWhen = deadlineParts[1].substring(3);

        DukeBot.Deadline deadline = new DukeBot.Deadline(deadlineParts[0], byWhen);
        return deadline;
    }
}
