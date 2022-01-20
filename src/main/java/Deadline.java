import java.util.*;
import java.lang.*;

public class Deadline extends Task {
    String deadline;

    public Deadline(int rank, String description) {
        super(rank, description.split(" /by ")[0]);
        this.deadline = description.split(" /by ")[1];
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}