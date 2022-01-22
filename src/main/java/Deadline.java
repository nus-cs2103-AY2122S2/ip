public class Deadline extends Task{
    String time;

    Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    public void print() {
        System.out.print("[D]");
        System.out.print("[" + (this.completed ? "x" : " ") +  "] " + this.description);
        System.out.println(" (by: " + this.time + ")");
    }

    @Override
    public String[] getDetails() {
        String[] details = super.getDetails();
        details[0] = TaskType.DEADLINE.toString();
        details[3] = time;
        return details;
    }
}
