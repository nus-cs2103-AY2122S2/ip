public class Task {
    private String objective;

    public Task(String objective) {
        this.objective = objective;
    }

    @Override
    public String toString() {
        return this.objective;
    }
}
