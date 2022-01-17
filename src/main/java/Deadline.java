class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public Task mark() {
        return new Deadline(this.description, this.by, true);
    }
    
    @Override
    public Task unmark() {
        return new Deadline(this.description, this.by, false);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
