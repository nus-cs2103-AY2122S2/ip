public abstract class Task {
    String status = "[ ]"; // for all new tasks added to list, they are initially not done.
    String description;
    int id;
    public static int count = 0;

    public Task(String description) {
        this.description = description;
        this.id = count;
        count++;
    }

    public void mark() {
        this.status = "[X]";
    }

    public void unmark() {
        this.status = "[ ]";
    }

    public abstract String track();
}
