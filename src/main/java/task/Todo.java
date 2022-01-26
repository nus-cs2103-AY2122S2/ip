package task;

public class Todo  extends Task {
    public String tag;

    public Todo(String description){
        super(description, "T");
        this.tag = "T";
    }

    public String getTag(){
        return "[" + tag + "]";
    }
}
