package duke.info.task;

public class Todo extends Task {

    /**
     * Constructs a Todo with the specified todo, isComplete information
     * @param todo - the details of the todo to be added
     * @param isComplete - true if the task is already complete
     */
    public Todo(String todo, boolean isComplete) {
        super(todo, "todo", isComplete);
    }

    /**
     * Returns a string representation of the Todo. The string consists of "[T]" to represent
     * the Todo task type, followed by the details of the todo.
     * @return - a string representation of the Todo.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
