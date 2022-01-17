public class Task {
    private String descriptor;

    private boolean completed;

    public Task(String descriptor) {
        this.descriptor = descriptor;
        this.completed = false;
    }

    public void markDone() {
        this.completed = true;
    }

    @Override
    public String toString() {
        char c = completed ? 'X' : ' ';
        return String.format("[%c] ", c).concat(descriptor);
    }
}
