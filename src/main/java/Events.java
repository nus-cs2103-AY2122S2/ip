public class Events extends Task{

    private String time;


    Events(String description, String time) {
        super(description);
        this.time = time;
    }

    public void print() {
        System.out.print("[E]");
        System.out.print("[" + (this.completed ? "x" : " ") +  "] " + this.description);
        System.out.println(" (at: " + this.time + ")");
    }
}
