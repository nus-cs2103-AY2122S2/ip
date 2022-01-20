public class ToDo extends Task {
    String prefix = "[T]";

    ToDo(String name) throws EmptyDescriptionException {
        super(name);
        if (name.length() == 0) {
            throw new EmptyDescriptionException("");
        }
    }

    @Override
    public String toString() {
        return prefix + super.toString();
    }
}
