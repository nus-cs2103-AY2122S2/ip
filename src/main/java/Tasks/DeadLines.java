package Tasks;

public class DeadLines extends Task {
    private final String details;

    public DeadLines(String task, Boolean marked, String details) {
        super(task, marked);
        this.details = details;
    }

    @Override
    public String cacheString() {
        String s = getMarked() ? "1" : "0";
        return "D" + "|" + s + "|" + this.getTask() + "|"  + this.details;
    }

    @Override
    public String toString() {
        if (this.getMarked()) {
            return "[D]" + "[X" + "] " + this.getTask() + " (by: " + this.details + ")";
        } else {
            return "[D]" + "[ " + "] " + this.getTask() + " (by: " + this.details + ")";
        }
    }
}
