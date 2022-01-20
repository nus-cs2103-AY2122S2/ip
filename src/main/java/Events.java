import java.util.*;

/**
 * tasks that STARTS at a specific time and ENDS at a specific time.
 */
public class Events extends Task {

    public Events(String description) {
        super(description);
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "[E]" + super.getStatusIcon() + " " + super.getDescription();
    }

}
