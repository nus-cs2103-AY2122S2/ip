package duke.task;

public abstract class Task {

    boolean checked = false;

    public void setChecked() {
        this.checked = true;
    }

    public void setUnChecked(){
        this.checked = false;
    }
}
