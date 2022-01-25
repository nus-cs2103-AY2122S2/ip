public abstract class Task {
    String title;
    boolean checked = false;

    public Task(String title) {
        this.title = title;
    }

    public void setChecked() {
        this.checked = true;
    }

    public void setUnChecked(){
        this.checked = false;
    }
}
