public class Task {
    String status = "[ ]"; // for all new tasks added to list, they are initially not done.
    String name;
    int id;
    public static int count = 0;

    public Task(String name) {
        this.name = name;
        this.id = count;
        count++;
    }

    public void mark() {
        this.status = "[X]";
    }

    public void unmark() {
        this.status = "[ ]";
    }

    @Override
    public String toString() {
        return this.name;
    }
}
