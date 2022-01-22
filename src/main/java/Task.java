public class Task {
    String description;
    Boolean completed = false;

    Task(String description){
        this.description = description;
    }

    public void markCompleted(){
        this.completed = true;
    }

    public void markIncompleted(){
        this.completed = false;
    }

    public void print() {
        System.out.println("[" + (this.completed ? "x" : " ") +  "] " + this.description);
    }

    public String[] getDetails() {
        String[] details = new String[4];
        details[1] = completed ? "1" : "0";
        details[2] = description;
        return details;

    }
}
