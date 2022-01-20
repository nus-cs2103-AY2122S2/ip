import java.lang.*;
import java.util.*;

public class Event extends Task {
    String at;

    public Event(int rank, String description) {
        super(rank, description.split(" /at ")[0]);
        this.at = description.split(" /at ")[1];
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}