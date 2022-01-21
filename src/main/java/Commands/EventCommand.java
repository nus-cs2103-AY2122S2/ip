package Commands;

import Exceptions.EmptyDescriptionException;
import Exceptions.DukeException;

public class EventCommand {
    public static DukeBot.Event preProcessing(String input, String[] parts) throws DukeException {
        if (parts.length == 1) {
            throw new EmptyDescriptionException("event");
        }
        String description = input.substring(6);
        String eventParts[] = description.split("/");
        String byWhen = eventParts[1].substring(3);

        DukeBot.Event event = new DukeBot.Event(eventParts[0], byWhen);
        return event;
    }
}
