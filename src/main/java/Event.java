import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a Event which is a subclass of Task
 * Overrides toString() from Task
 */
public class Event extends Task {
    private String dueDate;
    public Event (String name, String time) { super(name); this.dueDate = time;}

    /**
     * @override
     * @return String of Event task, eg: [E][X] Event
     */
    public String toString() { return "[E]" + super.toString() + "(at:" + this.dueDate + ")"; }
}