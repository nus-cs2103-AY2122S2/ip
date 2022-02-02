import java.util.*;

/**
 * Tasks without any date/time attached to it.
 */
public class ToDos extends Task {

    public ToDos(String description) {
        super(description);
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "[T]" + "[" + super.getStatusIcon() + "] " + super.getDescription();
    }
}
