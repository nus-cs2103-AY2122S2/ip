public class Task {
    private String description;

    public Task(String details) {
        this.description = details;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
