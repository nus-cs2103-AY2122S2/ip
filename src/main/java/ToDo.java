public class ToDo extends Task {

    public ToDo(String content) {
        super(content);
    }

    @Override
    public String toString() {
        if (getIsDone()) {
            return "    [T][X] " + getContent();
        } else {
            return "    [T][ ] " + getContent();
        }
    }
}
