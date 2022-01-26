import java.io.Serializable;

public abstract class Task implements Serializable {

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
        Ui.printMarkCompletion(this);
    }

    public void markNotCompleted(){
        this.isFinished = false;
        Ui.printMarkUncompletion(this);
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
