public class Task {
    private String name;
    private boolean done;
    Task(String name) {
        this.name = name;
        this.done = false;
    }

    public boolean getStatus() {
        return done;
    }

    public void markDone() {
        done = true;
    }

    public void markNotDone() {
        done = false;
    }

    public String getName() {
        return name;
    }
}
