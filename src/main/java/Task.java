public class Task {

    public boolean done;
    public String desc;
    public String type;

    public Task(String desc) {
        this.desc = desc;
        this.done = false;
        this.type = " ";
    }
    public Task(String desc, String type) {
        this.desc = desc;
        this.done = false;
        this.type = type;
    }
    public Task(String desc, boolean done, String type) {
        this.desc = desc;
        this.done = done;
        this.type = type;
    }
    public String getDone() {
        return (this.done ? "X" : " "); // mark done task with X
    }
    public String getType() {
        return this.type;
    }
//    public String getDesc() {
//        return this.desc;
//    }
    public Task mark() {
        return new Task(this.desc, true, this.type);
    }
    public Task unmark() {
        return new Task(this.desc);
    }

    public String getBy() {
        return "";
    }

//    public String toString() {
//        System.out.println("hey");
//        return this.desc;
//    }
}
