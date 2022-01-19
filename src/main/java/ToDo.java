public class ToDo extends Task {

    protected String type;

    public ToDo(String description) {
        super(description);
        this.type = "todo";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
