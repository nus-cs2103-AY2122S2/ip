public class Deadline extends Task {
    protected final String sym = "D";
    protected String day;

    Deadline (String description, String day) {
        super(description);
        this.day = day;
    }

    @Override
    String getSym() {
        return this.sym;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s]%s (by:%s)", sym, super.getStatusIcon(), super.description, this.day);
    }

}
