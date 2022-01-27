package duke.task;

import duke.exception.InvalidArgumentException;

import java.util.Arrays;
import java.util.List;

public class Event extends Task {
    private final String at;

    Event(String name, String at) {
        super(name);
        this.at = at;
    }

    public static Event of (String[] description) throws InvalidArgumentException {
        return Event.of(Arrays.asList(description));
    }

    public static Event of(List<String> description) throws InvalidArgumentException {
        if(description.size() == 1) {
            throw new InvalidArgumentException();
        }
        int index = description.indexOf("/at");
        String name = String.join(" ", description.subList(1, index));
        String at = String.join(" ", description.subList(index + 1, description.size()));
        return new Event(name, at);
    }

    @Override
    public String toStorageString() {
        String status = getStatus()? "X" : ".";
        return String.format(status + " event " + getName() + " /at " + at);
    }

    @Override
    public String toString() {
        return String.format("[E]" + super.toString() + "(at: " + at + ")");
    }
}
