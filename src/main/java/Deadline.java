class Deadline extends Task {

    private String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    public Deadline(String description, boolean hasCompleted, String date) {
        super(description);
        this.status = hasCompleted;
        this.date = date;
    }

    @Override
    public String toString() {
        return "[" + Type.D + "]";
    }

    public String getDate() {
        return this.date;
    }

    @Override
    String getDescription() {
        return this.description + " (by: " + date + ")";
    }
}