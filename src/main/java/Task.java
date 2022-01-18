public class Task implements ICompletable {

    protected Object item;
    protected boolean completed;

    public Task (Object obj) {
        item = obj;
        completed = false;
    }

    public void setCompleted(boolean value) {
        completed = value;
    }

    public String toString() {
        String display = completed ? "[X]" : "[ ]";
        return display + " " + item.toString();
    }
}