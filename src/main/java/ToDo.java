public class ToDo extends Task {

    public ToDo(String task) {
        super(task);
    }

    public String toString(){
        return String.format("[T]%s %s", this.statusString(), this.task);
    }
}