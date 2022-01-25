package duke.task;

import java.util.Objects;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == getClass()) {
            Todo todo = (Todo) obj;
            return todo.getDescription().equals(this.getDescription());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getDescription());
    }
}