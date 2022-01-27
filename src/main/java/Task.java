public class Task {
    boolean done;
    String task;
    String type;

    Task(String task, String type) {
        this.done = false;
        this.task = task;
        this.type = type;
    }

    void mark() {
        this.done = true;
    }

    void unmark() {
        this.done = false;
    }

    String saveFormat() {
        if (this.done) {
            return this.type + "/1/" + this.task;
        } else {
            return this.type + "/0/" + this.task;
        }
    }


    @Override
    public String toString() {
        if (this.done) {
            return "[" + this.type + "] [X] " + this.task;
        } else {
            return "[" + this.type + "] [ ] " + this.task;
        }
    }
}
