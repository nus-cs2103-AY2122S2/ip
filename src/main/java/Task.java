public abstract class Task {
    final String action;
    private boolean done;
    private final String type;

    Task(String action, String type) {
        this.action = action;
        this.done = false;
        this.type = type;
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
