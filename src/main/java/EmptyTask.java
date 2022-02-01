public class EmptyTask extends Task {
    EmptyTask() {
        super("EMPTY");
    }

    @Override
    public boolean isEmptyTask() {
        return true;
    }
}
