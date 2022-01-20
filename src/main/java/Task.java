public class Task {
    private String name;
    private boolean isChecked;

    public Task(String name, boolean isChecked) {
        this.name = name;
        this.isChecked = isChecked;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public boolean getChecked() {
        return this.isChecked;
    }

    public void setChecked(boolean check) {
        this.isChecked = check;
    }
}
