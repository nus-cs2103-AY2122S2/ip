package duke;

import exceptions.DukeEventException;
import java.io.IOException;

public class Event extends Task {

    protected String at;

    public Event(String name, String at) {
        super(name);
        this.at = at;
    }

    public static Event setEvent(String input) throws IOException {
        String eventName;
        String eventAt;


        try {
            String[] split = input.split(" /at ");
            eventName = split[0];
            eventAt = split[1];
            Event e = new Event(eventName, eventAt);
            return e;
        } catch (Exception e) {
            DukeEventException error = new DukeEventException(
                    "OOPS!!! Please enter in format: event <event> /at <event venue> \n " +
                    "e.g. event Lesson /at Com1");
            System.out.println(error.getMessage());
        }
        return null;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + at + ")";
    }
}
