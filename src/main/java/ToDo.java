public class ToDo extends Task {
    ToDo(String description) {
        super(description);
    }

    ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toWrite() {
        int bool;

        if(isDone) {
            bool = 1;
        } else {
            bool = 0;
        }

        return "T~" + bool + "~" + getDescription() + System.lineSeparator();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
