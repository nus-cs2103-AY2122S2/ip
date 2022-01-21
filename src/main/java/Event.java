public class Event extends Task {
    String time;

    public Event (String task, boolean done, String time) {
        super(task, done);
        this.time = time;
    }

    public String fileFormat() {
        return String.format("E | %s | %s | %s", this.done ? "X" : " ", this.task, this.time);
    }

    public String toString() {
        return String.format("[E][%s] %s (at %s)", this.done ? "X" : " ", this.task, this.time);
    }
}