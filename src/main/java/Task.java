/**
 * Task is contained in the TaskList: it contains description, done status and id.
 */
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

    void markDone() throws BernieException {
        if (isDone) {
            throw new BernieException("Cannot mark a task already done!");
        } else {
            this.isDone = true;
        }
    }

    void markNotDone() throws BernieException {
        if (!isDone) {
            throw new BernieException("Cannot unmark a task not done!");
        } else {
            this.isDone = false;
        }
    }

    @Override
    public String toString() {
        return isDone ? String.format("[X] %s", description) :
                        String.format("[ ] %s", description);
    }
}
