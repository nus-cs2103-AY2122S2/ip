package duke;

public class ToDos extends Task {

    public ToDos(String d) {
        super(d);
        this.type = "T";
    }

    public ToDos(String d, String done) {
        super(d);
        this.type = "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}