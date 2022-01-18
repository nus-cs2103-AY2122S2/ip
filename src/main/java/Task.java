public class Task {

    public boolean done;
    public String desc;

    public Task(String desc) {
        this.desc = desc;
        this.done = false;
    }
    public Task(String desc, boolean done) {
        this.desc = desc;
        this.done = done;
    }
    public String getDone() {
        return (this.done ? "X" : " "); // mark done task with X
    }
//    public String getDesc() {
//        return this.desc;
//    }
    public Task mark() {
        return new Task(this.desc, true);
    }
    public Task unmark() {
        return new Task(this.desc);
    }
}
