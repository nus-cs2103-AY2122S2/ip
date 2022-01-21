public class Deadline extends Task {
    String time;

    public Deadline (String task, boolean done, String time) {
        super(task, done);
        this.time = time;
    }

    public String fileFormat() {
        return String.format("D | %s | %s | %s", this.done ? "X" : " ", this.task, this.time);
    }

    public String toString() {
        return String.format("[D][%s] %s (by %s)", this.done ? "X" : " ", this.task, this.time);
    }
}