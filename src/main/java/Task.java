import java.util.Arrays;

public class Task {
    protected String d;
    protected boolean done;

    public Task(String d) {
        this.d = d;
        this.done = false;
    }

//    public Task(String[] arr) {
//        this.done = false;
//        this.d = Arrays.toString(arr);
//        this.d = this.d.substring(1, this.d.length()-1).replaceAll(",", "");
//    }

    public String markString() {
        return (done ? "[X]" : "[]");
    }

    public void setDone() {
        this.done = true;
    }

    public void setUndone(){
        this.done = false;
    }

    @Override
    public String toString() {
        return markString() + " " + d;
    }
}