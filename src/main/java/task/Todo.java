package task;

/**
 * Class that encapsulates task types without due date.
 */
public class Todo extends Task {

    public Todo(String details) {
        super(details);
    }

    /**
     * Returns character symbol that represents todo tasks.
     * @return String 'T'.
     */
    public String symbol() {
        return "T";
    }

    /**
     * Returns task details since it does not have a due date.
     * @return details of the todo.
     */
    @Override
    public String displayTime() {
        return super.toString();
    }

}
