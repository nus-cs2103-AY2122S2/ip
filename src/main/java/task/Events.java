package task;

//A variant of task
public class Events extends Tasks {
    String timing; // Timing of event

    // Constructor
    public Events(String taskName, String timing) {
        super(taskName);
        this.timing = timing;
    }

    public Events(String taskName, boolean completion, String timing) {
        super(taskName, completion);
        this.timing = timing;
    }

    // Get timing of event
    String getTiming() {
        return "(at: " + timing + ")";
    }

    // Completion of task
    @Override
    public Events completeTask() {
        return new Events(super.getName(), true, timing);
    }

    // Uncomplete the task
    @Override
    public Events uncompleteTask() {
        return new Events(super.getName(), false, timing);
    }

    // Format of saving to database
    public String toDatabaseString() {
        return "E | " + (this.getCompletion() == true ? "X" : " ") + " | " + super.getName()
                + " | " + timing + "\n";
    }

    // toString returning event
    public String toString() {
        return "[E][" + (this.getCompletion() == true ? "X" : " ") + "] " + super.getName()
                + " (at: " + timing + ")";
    }
}