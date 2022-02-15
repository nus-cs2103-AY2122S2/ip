package chatcat.tasks;

/**
 * The default Todo class inherited from {@code Task}.
 *
 * @see Task
 */
public class Todo extends Task {

    String todoStr;

    /**
     * Creates a {@code Todo} object using a specified description.
     *
     * @param todo the description of this task.
     */
    public Todo(String todo) {
        super(todo);
        this.todoStr = todo;
    }

    /**
     * Returns a representation in string of {@code Todo} task.
     *
     * @return a representation in string of {@code Todo} task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Checks if this todo {@code Todo} instance is the same as
     * another todo {@code Todo} instance.
     *
     * Logic for duplicate extension was referenced from Ng Jun Kang
     * https://github.com/ngjunkang/ip.git
     *
     * @param o object {@code Object} to compare with this todo {@code Todo} instance.
     * @return true if parameter has the same description as this
     * todo {@code Todo} instance.
     */
    @Override
    public boolean equals(Object o) {

        if (o == null) {
            return false;
        }

        if (!(o instanceof Todo)) {
            return false;
        }

        if (o == this) {
            return true;
        }

        Todo todo = (Todo) o;

        return this.todoStr.equals(todo.todoStr);
    }
}
