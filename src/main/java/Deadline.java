public class Deadline extends Task {
    String by;

    public Deadline(String activity, String type, String by) {
        super(activity, type);
        this.by = by;
    }
    @Override
    public void getStatus() {
        if (this.status == 0) {
            System.out.println("[" + type + "][ ] " + activity + " (by " + by + " )");
        } else {
            System.out.println("[" + type + "][X] " + activity + " (by " + by + " )");
        }
    }
}
