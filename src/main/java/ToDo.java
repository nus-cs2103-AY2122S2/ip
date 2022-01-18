public class ToDo extends Task {
    protected String type;

    ToDo(String name) {
        super(name);
        this.type = "T";
    }


    @Override
    public String toString() {
        String status = this.isDone ? "X" : " ";
        return "[" + this.type + "][" + status + "]" + this.name;
    }
}
