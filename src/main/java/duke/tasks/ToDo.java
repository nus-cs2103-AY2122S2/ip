package duke.tasks;

public class ToDo extends Task {

    public ToDo (String name) {
        super(name);
        this.info = "T,0," + name;
    }

    @Override
    public void mark() {
        this.isDone = true;
        this.info = "T,1," + name;
    }

    @Override
    public void unmark() {
        this.isDone = false;
        this.info = "T,0," + name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
