public class Task {
    private String name;
    private boolean done;
    private String dead;
    private String type;

    public Task(String type, String name) {
        this.name = name;
        this.done = false;
        this.type = type;
    }
    public Task(String type, String name, String dead) {
        this.name = name;
        this.done = false;
        this.type = type;
        this.dead = dead;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    @Override
    public String toString() {
        String str = "[";
        //Type
        if (this.type.equals("todo")) {
            str += "T][";
        } else if (this.type.equals("deadline")) {
            str += "D][";
        } else {
            str += "E][";
        }
        //Completion
        if (this.done) {
            str += "X";
        } else {
            str += " ";
        }
        str += "] " + this.name;
        if (this.type.equals("deadline")) {
            str += "(by:" + this.dead + ")";
        } else if (this.type.equals("event")){
            str += "(at:" + this.dead + ")";
        }
        return str;
    }
}
