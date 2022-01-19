public class Deadline extends Task {
    private String time;
    public Deadline(String name, String time) {
        super(name);
        this.time = time;
        System.out.println("[D][ ] " + name + " (by: " + time +")");
    }

    @Override
    public String getStatus() {
        return "[D]" + super.getStatus() +" (by: " + time +")";
    }

    @Override
    public void markDone() {
        System.out.print("[D]");
        super.markDone();
        System.out.println(" (by: " + time +")");
    }

    @Override
    public void markNotDone() {
        System.out.print("[D]");
        super.markNotDone();
        System.out.println(" (by: " + time +")");
    }
}
