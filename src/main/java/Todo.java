public class Todo extends Task {
    protected final String sym = "T";

    Todo (String description) {
        super(description);
    }

    @Override
    String getSym() {
        return this.sym;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s]%s", sym, super.getStatusIcon(), super.description);
    }

}
