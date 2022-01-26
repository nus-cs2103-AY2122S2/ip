class DeadLines extends Task {
    private final String details;

    public DeadLines(String task, Boolean marked, String details) {
        super(task, marked);
        this.details = details;
    }

    @Override
    public String toString() {
        if (this.getMarked()) {
            return "[D]" + "[X" + "] " + this.getTask() + " (by: " + returnDate(this.details) + ")";
        } else {
            return "[D]" + "[ " + "] " + this.getTask() + " (by: " + returnDate(this.details) + ")";
        }
    }
}
