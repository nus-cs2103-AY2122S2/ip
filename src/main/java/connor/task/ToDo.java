package connor.task;

/**
 * Represents a {@code Task} of type {@code ToDo}.
 */
public class ToDo extends Task {

    /**
     * Constructor for {@code ToDo} class.
     *
     * @param desc Description of the task.
     */
    public ToDo(String desc) {
        super(desc, TaskType.TODO);
    }

    /**
     * Returns a {@code String} representation of a {@code ToDo Task}.
     *
     * @return A {@code String} representation of a {@code ToDo Task}.
     */
    @Override
    public String toString() {
        return getType() + super.toString();
    }

    /**
     * Compares the ToDo object with another object and returns {@code true}
     * if and only if their descriptions and statuses are the same.
     *
     * @param o Object to compare with.
     * @return {@code true} if and only if the two ToDos have the same
     * description and status, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ToDo)) {
            return false;
        } else {
            ToDo t = (ToDo) o;
            return this.getDesc().equals(t.getDesc())
                    && this.isDone().equals(t.isDone());
        }
    }
}
