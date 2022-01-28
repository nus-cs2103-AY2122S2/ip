public class Task {
    private String name;
    private boolean isChecked;
    private String taskLabel;

    public Task(String name, boolean isChecked, String taskLabel) {
        this.name = name;
        this.isChecked = isChecked;
        this.taskLabel = taskLabel;
    }

    @Override
    public String toString() {

        return this.name;
    }

    public boolean getChecked() {
        return this.isChecked;
    }

    public String getTaskLabel() {
        return this.taskLabel;
    }

    public void setChecked(boolean check) {
        this.isChecked = check;
    }

    public String isTaskCheck() {
        return this.isChecked ? "X" : " ";
    }
}
