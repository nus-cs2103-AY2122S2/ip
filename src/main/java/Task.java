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
}
