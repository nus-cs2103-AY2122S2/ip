package duke.task;

public class ToDo extends Task{
    public ToDo(String name, boolean isChecked, String taskLabel) {

        super(name, isChecked, taskLabel);
    }

    @Override
    public String toString() {
        return "[" + super.getTaskLabel() + "][" + super.isTaskCheck() + "] " + super.toString();
    }
}
