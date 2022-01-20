public class ToDo extends Task{
    public ToDo(String name, boolean isChecked, String taskLabel) {
        super(name, isChecked, taskLabel);
    }

    public String isTaskCheck() {
        return super.getChecked() ? "X" : " ";
    }

    @Override
    public String toString() {
        return "[" + super.getTaskLabel() + "][" + isTaskCheck() + "] " + super.toString();
    }
}
