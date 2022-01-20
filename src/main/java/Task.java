public class Task {
    protected String description;

    Task(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
