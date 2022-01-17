public class Deadline extends Task {

    protected String by;
    private static boolean tipShown = false;

    public Deadline(String description) {
        super(description);
        if (!tipShown) {
            System.out.println("Tip: You can specify deadline");
            System.out.println("eg. deadline CS2103T /by today\n");
            tipShown = true;
        }
    }

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        tipShown = true;
    }

    @Override
    public String toString() {
        if (by != null) {
            return "[D]" + super.toString() + " (by: " + by + ")";
        } else {
            return "[D]" + super.toString();
        }
    }
}
