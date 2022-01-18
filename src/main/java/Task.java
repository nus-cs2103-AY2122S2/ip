public class Task {
    protected String description;
    protected boolean isDone;
    protected int id;

    /**
     * Constructs a Task object with description, id and isDone
     * @param description String
     * @param id int, the taskNumber (+ 1 of index)
     */
    Task(String description, int id) {
        this.description = description;
        this.id = id;
        this.isDone = false;
    }

    void markDone() {
        this.isDone = true;
    }

    void markNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return isDone ? String.format("%d.[X] %s\n", id, description) :
                        String.format("%d.[ ] %s\n", id, description);
    }
}
