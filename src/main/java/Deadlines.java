import java.util.*;

/**
 * Tasks that need to be done before a specific time/date.
 */
public class Deadlines extends Task {

    public Deadlines(String description) {
        super(description);
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "[D]" + super.getStatusIcon() + " " + super.getDescription();
    }

}
