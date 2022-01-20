
public class Task {
    private final String name;
    private boolean marked;
    private final Type type;
    private String time = "";

    Task(String name, Type type) {
        this.marked = false;
        this.name = name;
        this.type = type;
    }

    public void mark() {
        this.marked = true;
    }

    public void setTime(String time) {
        if(this.type == Type.EVENT)
        this.time = "(at: " + time + ")";
        else this.time = "(by: " + time + ")";
    }

    @Override
    public String toString() {
        return "[" + type + "][" + (marked? "X" : " ") + "] " + name + time;
    }
}
