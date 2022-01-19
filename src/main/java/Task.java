public class Task {

    /** Stores a string description of the task **/
    protected String description;

    /** Stores whether the task has been completed **/
    protected boolean isFinished;


    // Constructor for Task Class
    public Task(String x, boolean y){
        this.description = x;
        this.isFinished = y;
    }

    public void markCompleted(){
        this.isFinished = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.toString());
    }

    public void markNotCompleted(){
        this.isFinished = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.toString());
    }

    @Override
    public String toString(){
        if(isFinished){
            String temp = "[X] " + description;
            return temp;
        } else {
            String temp = "[ ] " + description;
            return temp;
        }
    }

}
