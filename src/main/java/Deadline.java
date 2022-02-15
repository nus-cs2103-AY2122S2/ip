public class Deadline extends Task {
    private final String sym = "D";
    private String day;

    Deadline (String description, String day) {
        super(description);
        this.day = day;
    }

    String getSym() {
        return this.sym;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s]%s (by:%s)", sym, super.getStatusIcon(), super.getDescription(), this.day);
    }
}
