public class Event extends Task{

    private String time;


    Event(String description, String time) {
        super(description);
        this.time = time;
    }

    public void print() {
        System.out.print("[E]");
        System.out.print("[" + (this.completed ? "x" : " ") +  "] " + this.description);
        System.out.println(" (at: " + this.time + ")");
    }

    @Override
    public String[] getDetails() {
        String[] details = super.getDetails();
        details[0] = TaskType.EVENT.toString();
        details[3] = time;
        return details;
    }
}
