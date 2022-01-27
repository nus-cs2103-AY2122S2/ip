public class Todo extends Task {

    /**
     * Constructor for Todo class
     * @param description Name of the to-do task
     */
    public Todo(String description) {
        super(description);
    }

    public String whatType() {
        return "T";
    }

    /**
     * toString method specific for Todo class,
     * inherits toString() fromTask class while adding additional information
     * Like the type of task, [T]
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
