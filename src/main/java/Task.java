public class Task {
    private final String description;

    Task(String description) {
        this.description = description;
    }

    String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
