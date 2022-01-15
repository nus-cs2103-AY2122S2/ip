public class Deadlines extends Task{
    String time;

    Deadlines(String description, String time) {
        super(description);
        this.time = time;
    }

    public void print() {
        System.out.print("[D]");
        System.out.print("[" + (this.completed ? "x" : " ") +  "] " + this.description);
        System.out.println(" (by: " + this.time + ")");
    }
}
