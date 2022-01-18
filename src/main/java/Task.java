public abstract class Task {
    protected String name;
    protected boolean done;
    protected Character type;

    public Task(String n, boolean d) {
        name = n;
        done = d;
    }

    public String getName() {
        return name;
    }

    public boolean getDone() {
        return done;
    }

    public Character getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setType(Character type) {
        this.type = type;
    }

    //for displaying if a task is done or not
    public String getDoneIcon() {
        return getDone()
                ? "[X]"
                : "[ ]";
    }

    //for displaying task's representation
    public String getTaskIcon() {
        return String.format("[%c]", type);
    }
}
