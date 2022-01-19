public class ToDo extends Task {
    ToDo(String description, int id) {
        super(description, id);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
