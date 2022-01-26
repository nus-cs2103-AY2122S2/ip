public class ToDo extends Task {

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    public String getType() {
        return  "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}