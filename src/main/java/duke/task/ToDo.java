package duke.task;

import java.time.LocalDate;


/**
 * Represents a task that the user wants to do.
 *
 */
public class ToDo extends Task {
    public ToDo(String objective) {
        super(objective);
    }

    public ToDo(String objective, boolean done) {
        super(objective, done);
    }

    @Override
    public String serialize() {
        assert this.objective != null;
        return "T|" + (this.isDone ? "1|" : "0|") + this.objective + "\n";
    };

    @Override
    public boolean equals(Object o) {
        if (o instanceof ToDo) {
            ToDo t = (ToDo) o;
            return this.objective.equals(t.objective) && (this.isDone == t.isDone);
        } else {
            return false;
        }
    }

    @Override
    public boolean sameTime(LocalDate date) {
        return false;
    }

    @Override
    public int compareTo(Task other) {
        return 1;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
