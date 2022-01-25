public class ToDo extends Task {

    public ToDo(String task) {
        super(task);
    }

    public ToDo(String task, boolean isDone) {
        super(task, isDone);
    }

    public String toFileFormat() {
        Integer i = this.isDone ? 1 : 0;
        return String.format("T | %d | %s\n", i, this.task);
    }

    @Override
    public String toString(){
        return String.format("[T]%s %s", this.statusString(), this.task);
    }
}