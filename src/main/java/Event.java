import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a Event which is a subclass of Task
 * Overrides toString() from Task
 */
public class Event extends Task {
    public String dueDate;
    public Event (String name, String time) { super(name); this.dueDate = time;}

    /**
     * @override
     * @return String of Event task, eg: [E][X] Event (at:) vs [E] [✓] Event (at:)
     */
    public String toString() { return "[E]" + super.toString() + "(at:" + this.dueDate + ")"; }
}