package narcibot;
public class Task {
    private String name;
    private boolean done;

    public Task(String name) {
        this.name = name;
    }

    public Task(String name, boolean done) {
        this.name = name;
        this.done = done;
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

    public String save() {
        return (done ? "1" : "0" )+ "|" + name;
    }
}
