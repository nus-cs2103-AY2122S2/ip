public class Task {
    private String name;
    private boolean done;
    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public String getStatus() {
        String status = done ? "[X] " : "[ ] ";
        return status + name;
    }

    public void markDone() {
        System.out.print("[X] " + name);
        done = true;
    }

    public void markNotDone() {
        System.out.print("[ ] " + name);
        done = false;
    }
}
