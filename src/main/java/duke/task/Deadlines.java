package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

//A variant of duke.task
public class Deadlines extends Tasks {
    LocalDate deadline; // Deadline to complete deadline duke.task

    // Constructor
    public Deadlines(String taskName, String deadline) throws DateTimeParseException {
        super(taskName);
        this.deadline = LocalDate.parse(deadline);
    }

    public Deadlines(String taskName, boolean completion, String deadline) throws DateTimeParseException {
        super(taskName, completion);
        this.deadline = LocalDate.parse(deadline);
    }

    // Get deadline of duke.task
    public String getTiming() {
        return "(by: " + deadline + ")";
    }

    // Completion of duke.task
    @Override
    public Deadlines completeTask() {
        return new Deadlines(super.getName(), true, deadline.toString());
    }

    // Uncomplete the duke.task
    @Override
    public Deadlines uncompleteTask() {
        return new Deadlines(super.getName(), false, deadline.toString());
    }

    // Save to database format
    public String toDatabaseString() {
        return "D | " + (this.getCompletion() == true ? "X" : " ") + " | " + super.getName()
                + " | " + deadline + "\n";
    }

    // toString returning duke.task
    public String toString() {
        return "[D][" + (this.getCompletion() == true ? "X" : " ") + "] " + super.getName()
                + " (by: " + deadline + ")";
    }

}
