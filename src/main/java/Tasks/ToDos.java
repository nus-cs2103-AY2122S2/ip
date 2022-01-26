package Tasks;

public class ToDos extends Task {

    public ToDos(String task, Boolean marked) {
        super(task, marked);
    }

    @Override
    public String cacheString() {
        String s = getMarked() ? "1" : "0";
        return "T" + "|" + s + "|" + this.getTask();
    }

    @Override
    public String toString() {
        if (this.getMarked()) {
            return "[T]" + "[X" + "] " + this.getTask();
        } else {
            return "[T]" + "[ " + "] " + this.getTask();
        }
    }
}
