public class ToDo extends Task {

    public ToDo(String desc) {
        super(desc, "T");
    }
    public ToDo(String desc, boolean done) {
        super(desc, done, "T");
    }
    @Override
    public Task mark() {
        return new ToDo(this.desc, true);
    }
    @Override
    public Task unmark() {
        return new ToDo(this.desc, false);
    }

//    @Override
//    public String toString() {
//        return "[T]" + super.toString();
//    }
}
