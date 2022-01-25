package Duke.task;

import java.io.Serializable;

public class ToDo extends Task implements Serializable {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + super.getStatusIcon() + "] " + super.getDescription();
    }
}