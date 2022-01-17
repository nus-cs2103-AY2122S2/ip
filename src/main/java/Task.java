public class Task {
    private final String action;

    Task(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return this.action;
    }
}
