/**
 * Todo class represents an todo task.
 *
 * @author Rosa Kang
 */
public class Todo extends Task {
    /**
     * Constructor for Todo
     *
     * @param description a description of the deadline task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * toString method for Event.
     *
     * @return type of the task (event) marked by E and the description of the task followed by the date
     */

    @Override
    public String toString() {
        return "T " + super.toString();
    }
    /* public String toString() {
        return "[T]" + super.toString();
    }*/
}