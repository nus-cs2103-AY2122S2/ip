public class Deadline extends TaskWithDateTime {
    protected String by;

    public Deadline(String description, String by) {
        super(description, by);
        this.by = by;
        //LocalDate day = LocalDate.parse("2015-02-20");
    }

    @Override
    public String toString() {
        String s = "[D]" + super.toString();
        return s.replace(" (at: ", " (by: ");
    }
}
