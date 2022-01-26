public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toText() {
        return "T | " + (this.getDone() ? 1 : 0) + " | " + this.getName() + "\n";
    }
}
