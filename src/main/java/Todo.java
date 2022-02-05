import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a Todo which is a subclass of Task
 * Includes a dueDate attribute. Overrides toString() from Task
 */

public class Todo extends Task {

    /**
     * Constructor for Todo
     * @param name Name of Todo
     */
    public Todo (String name) {
        super(name);
    }

    /**
     * @override
     * @return String of Todo task, eg: [T][X] Todo
     */
    public String toString() {
        return "[T]" + super.toString();
    }

}