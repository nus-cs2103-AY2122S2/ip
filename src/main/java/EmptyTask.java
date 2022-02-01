public class EmptyTask extends Task {
    EmptyTask() {
        super("EMPTY");
    }

    public String getPrefix() {
        return "";
    }

    public String getPostfix() {
        return "";
    }

    @Override
    public boolean isEmptyTask() {
        return true;
    }
}
