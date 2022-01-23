public abstract class Task {

    protected String name;
    protected boolean isMark;

    public Task(String name) {
        this.name = name;
    }

    public Task(String name, boolean isMark) {
        this.name = name;
        this.isMark = isMark;
    }

    public void markTask() {
        isMark = true;
    }

    public void unmarkTask() {
        isMark = false;
    }

    public String toString() {
        return String.format("[%s] %s", isMark ? "X" : " ", name);
    }

    public String toData() {
        return String.format("%d|%s", isMark ? 1 : 0, name);
    }
}
