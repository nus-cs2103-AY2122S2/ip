package duke.task;

import java.time.LocalDate;


/**
 * Represents a task that the user wants to do.
 *
 */
public class ToDos extends Task {
    public ToDos(String objective) {
        super(objective);
    }

    public ToDos(String objective, boolean done) {
        super(objective, done);
    }

    @Override
    public String serialize() {
        assert this.objective != null;
        return "T|" + (this.done ? "1|" : "0|") + this.objective + "\n";
    };

    @Override
    public boolean equals(Object o) {
        if (o instanceof ToDos) {
            ToDos t = (ToDos) o;
            return this.objective.equals(t.objective) && (this.done == t.done);
        } else {
            return false;
        }
    }

    @Override
    public boolean sameTime(LocalDate date) {
        return false;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
