public class Task {
    private String name;
    private String status;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
