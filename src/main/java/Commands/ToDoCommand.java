package Commands;

import Exceptions.DukeException;
import Exceptions.EmptyDescriptionException;

public class ToDoCommand {
    public static DukeBot.ToDo preProcessing(String input, String[] parts) throws DukeException {
        if (parts.length == 1) {
            throw new EmptyDescriptionException("todo");
        }

        String description = input.substring(5);
        DukeBot.ToDo toDo = new DukeBot.ToDo(description);

        return toDo;
    }
}
