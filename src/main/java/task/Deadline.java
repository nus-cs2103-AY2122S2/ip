package task;

public class Deadline extends Task {

    /**
     * Deadline for current task in string
     */
    private final String deadline;

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String getDescription() {
        return String.format("[D]%s %s (by: %s)",
                super.getDoneStatusCheckbox(), super.getName(), this.deadline);
    }
}
