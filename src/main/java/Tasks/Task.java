package Tasks;

abstract public class Task {
    private String task;
    private Boolean marked;

    public Task(String task, Boolean marked) {
        this.task = task;
        this.marked = marked;
    }

    public void setMarked(Boolean var) {
        this.marked = var;
    }

    public String getTask() {
        return this.task;
    }

    public boolean getMarked() {
        return this.marked;
    }

    abstract  public String cacheString();

    @Override
    abstract public String toString();


}
