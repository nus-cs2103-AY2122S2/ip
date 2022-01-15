public class ToDo extends Task {
    String type = "T";

    ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + super.toString();
    }

}
