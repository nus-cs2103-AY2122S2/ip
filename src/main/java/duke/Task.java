package duke;

public class Task {
    protected String description;
    protected Boolean isCompleted = false;

    Task(String description){
        this.description = description;
    }

    public void markCompleted(){
        this.isCompleted = true;
    }

    public void markIncompleted(){
        this.isCompleted = false;
    }

    public void print() {
        System.out.println("[" + (this.isCompleted ? "x" : " ") +  "] " + this.description);
    }

    public String[] getDetails() {
        String[] details = new String[4];
        details[1] = isCompleted ? "1" : "0";
        details[2] = description;
        return details;

    }
}
