public class Event extends Task {
    private String time;
    public Event(String name, String time) {
        super(name);
        this.time = time;
        System.out.println("[E][ ] " + name + " (at: " + time +")");
    }

    public Event(String name, String time, boolean done) {
        super(name, done);
        this.time = time;
    }

    @Override
    public String getStatus() {
        return "[E]" + super.getStatus() + " (at: " + time +")";
    }

    @Override
    public void markDone() {
        System.out.print("[E]");
        super.markDone();
        System.out.println(" (at: " + time +")");
    }

    @Override
    public void markNotDone() {
        System.out.print("[E]");
        super.markNotDone();
        System.out.println(" (at: " + time +")");
    }

    @Override
    public String save() {
        return "E|" + super.save() + "|" + time;
    }
}
