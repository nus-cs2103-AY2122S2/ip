package task;

//A variant of task 
public class Deadlines extends Tasks{
    String deadline;    //Deadline to complete deadline task
    
    //Constructor
    public Deadlines(String taskName, String deadline){
        super(taskName);
        this.deadline = deadline;
    }

    public Deadlines(String taskName, boolean completion, String deadline){
        super(taskName, completion);
        this.deadline = deadline;
    }

    //Get deadline of task
    String getTiming(){
        return "(by: " + deadline + ")";
    }

    //Save to database format
    public String toDatabaseString(){
        return "D | " + (this.getCompletion()==true?"X":" ") + " | " + super.getName()
            + " | " + deadline + "\n";
    }

    //toString returning task
    public String toString(){
        return "[D][" + (this.getCompletion()==true?"X":" ") + "] " + super.getName() 
            + " (by: " + deadline + ")";
    }

}

