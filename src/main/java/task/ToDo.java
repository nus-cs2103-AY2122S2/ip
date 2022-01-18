package task;

public class ToDo extends Task {

    public ToDo(String name) {
        super(name);
    }

    @Override
    public String getDescription() {
        return String.format("[T]%s %s",
                super.getDoneStatusCheckbox(), super.getName());
    }
}
