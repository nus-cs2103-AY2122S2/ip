package duke.task;

public abstract class Task {

    boolean isChecked = false;

    public void setChecked() {
        this.isChecked = true;
    }

    public void setUnchecked() {
        this.isChecked = false;
    }
}
