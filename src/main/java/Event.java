class Event extends Task {

    private String date;

    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    public Event(String description, boolean hasCompleted, String date) {
        super(description);
        this.status = hasCompleted;
        this.date = date;

    }

    @Override
    public String toString() {
        return "[" + Type.E + "]";
    }
    public String getDate() {
        return this.date;
    }

    @Override
    String getDescription() {
        return this.description + " (at: " + date + ")";
    }
}