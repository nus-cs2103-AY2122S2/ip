public class EmptyTask extends Task {
    EmptyTask() {
        super("EMPTY");
    }

    public String getPrefix() {
        return "";
    }

    @Override
    public String getDate() {
        return "";
    }

    @Override
    public String getTime() {
        return "";
    }

    @Override
    public boolean isEmptyTask() {
        return true;
    }
}
