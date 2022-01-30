public class ToDo extends Task {
    String prefix = "[T]";

    ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return prefix + super.toString();
    }
}
