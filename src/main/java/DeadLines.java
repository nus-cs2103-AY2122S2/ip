import java.lang.reflect.Array;
import java.util.ArrayList;

public class DeadLines extends Task {
    String deadline;

    public DeadLines (String task, String deadline) throws DukeException {
        super(task);
        this.deadline = deadline;
        this.initials = "D";
        if (task.length() < 1) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + this.deadline + ")";
    }

    @Override
    public ArrayList<String> makeCompact() {
        ArrayList<String> out = super.makeCompact();
        out.add(deadline);
        return out;
    }
}
