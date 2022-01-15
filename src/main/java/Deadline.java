public class Deadline extends Task{
    private String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String identify(){
        String output = deadline.replaceFirst(" ", ": ");
        if (super.getIsDone()) {
            return String.format("[D][X] %s (%s)", super.getDescription(), output);
        } else {
            return String.format("[D][ ] %s (%s)", super.getDescription(), output);
        }
    }
}
