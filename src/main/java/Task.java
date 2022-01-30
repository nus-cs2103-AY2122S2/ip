public abstract class Task {
    protected String name;
    protected boolean state;
    // task state: true = done, false = not done

    Task(String name) {
        this.name = name;
        this.state = false;
    }

    public void mark() {
        this.state = true;
    }

    public void unmark() {
        this.state = false;
    }

    public boolean isMarked() {
        return this.state;
    }


    @Override
    public String toString() {
        String box = "";
        if (this.state)
            box += "[X] ";
        else
            box += "[ ] ";
        return box + this.name;
    }

}
