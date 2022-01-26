package Tasks;

public class Events extends Task {
     private final String details;

    public Events(String task, Boolean marked, String details) {
        super(task, marked);
        this.details = details;
    }

    @Override
    public String toString() {
        if (this.getMarked()) {
            return "[E]" + "[X" + "] " + this.getTask() + " (at: " + this.details + ")";
        } else {
            return "[E]" + "[ " + "] " + this.getTask() + " (at: " + this.details + ")";
        }
    }
}
