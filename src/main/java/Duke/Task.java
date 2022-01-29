package Duke;

public class Task {
    private String descriptor;

    private boolean completed;

    public Task(String descriptor) {
        this.descriptor = descriptor;
        this.completed = false;
    }

    public Task(String descriptor, boolean completed) {
        this.descriptor = descriptor;
        this.completed = completed;
    }

    public boolean markDone() {
        if (!this.completed) {
            this.completed = true;
            return true;
        } else {
            return false;
        }
    }

    public boolean markUndone() {
        if (this.completed) {
            this.completed = false;
            return true;
        } else {
            return false;
        }
    }

    public boolean matchString(String toCompare) {
        return this.descriptor.contains(toCompare);
    }

    @Override
    public String toString() {
        char c = completed ? 'X' : ' ';
        return String.format("[%c] %s", c, descriptor);
    }
}
