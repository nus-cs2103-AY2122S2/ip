public class Task {
    private final String action;
    private boolean done;

    Task(String action) {
        this.action = action;
        this.done = false;
    }

    void complete() {
        this.done = true;
    }

    void incomplete() {
        this.done = false;
    }

    @Override
    public String toString() {
        String product = "";
        if (done) {
            product += "[X] ";
        } else {
            product += "[ ] ";
        }
        product += this.action;
        return product;
    }
}
