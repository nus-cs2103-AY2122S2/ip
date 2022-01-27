package tesseract.task;

import tesseract.task.Task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toMemoryString() {
        return "T" + super.toMemoryString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
