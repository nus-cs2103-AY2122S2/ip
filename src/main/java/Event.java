public class Event extends Task {
    String timing;

    public Event (String task, String timing) throws DukeException {
        super(task);
        this.timing = timing;
        if (task.length() < 6) {
            throw new DukeException("     ☹ OOPS!!! The description of a event cannot be empty.");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + this.timing + ")";
    }
}
