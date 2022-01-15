public class Event extends Task{
    private String deadline;

    public Event(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String identify(){
        String output = deadline.replaceFirst(" ", ": ");
        if (super.getIsDone()) {
            return String.format("[E][X] %s (%s)", super.getDescription(), output);
        } else {
            return String.format("[E][ ] %s (%s)", super.getDescription(), output);
        }
    }
}
