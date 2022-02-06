public class Todo extends Task {
    private final String sym = "T";

    Todo (String description) {
        super(description);
    }

    String getSym() {
        return this.sym;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s]%s", sym, super.getStatusIcon(), super.getDescription());
    }
}
