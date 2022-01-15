public class Task {
    protected String name;
    protected boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public String toggleDone() {
        if (this.done) {
            return "X";
        }
        else {
            return " ";
        }
    }
}
