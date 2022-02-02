import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Represents a Deadline which is a subclass of Task
 * Includes a dueDate attribute. Overrides toString() from Task
 */
public class Deadline extends Task {
    public String dueDate;
    public Deadline(String name, String time) {super(name); this.dueDate = time;}

    /**
     * @override
     * @return String of Deadline task, eg [D][X] Deadline (by:XX) vs [D][✓] Deadline (by;XX)
     */

    public String toString() { return "[D]" + super.toString() + "(by:" + this.dueDate + ")"; }
}