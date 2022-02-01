package duke.task;

import duke.exception.InvalidArgumentException;

import java.util.Arrays;
import java.util.List;

public class Event extends Task {
    private final String at;

    /**
     * Constructs Event class.
     * Event constructor accepts the description of the task and when the task will be done.
     *
     * @param name Description of the event task.
     * @param at   Time indicator of the event task.
     */
    Event(String name, String at) {
        super(name);
        this.at = at;
    }

    /**
     * Constructs an Event object as a factory constructor.
     *
     * @param description Description of the Event task in array format.
     * @return the Event object.
     * @throws InvalidArgumentException If there is no description or time indicator.
     */
    public static Event of(String[] description) throws InvalidArgumentException {
        return Event.of(Arrays.asList(description));
    }

    /**
     * Constructs an Event object as a factory constructor.
     *
     * @param description Description of the event task in List format.
     * @return the Event object.
     * @throws InvalidArgumentException If there is no description or time indicator.
     */
    public static Event of(List<String> description) throws InvalidArgumentException {
        if (description.size() == 1) {
            throw new InvalidArgumentException();
        }
        int index = description.indexOf("/at");
        String name = String.join(" ", description.subList(1, index));
        String at = String.join(" ", description.subList(index + 1, description.size()));
        return new Event(name, at);
    }

    /**
     * Returns text representing the event task.
     * This method is used for storing event task.
     *
     * @return Task in text format.
     */
    @Override
    public String toStorageString() {
        String status = getStatus() ? "X" : ".";
        return String.format(status + " event " + getName() + " /at " + at);
    }

    /**
     * Returns text representing the event task for User.
     *
     * @return Task in text format.
     */
    @Override
    public String toString() {
        return String.format("[E]" + super.toString() + "(at: " + at + ")");
    }
}
