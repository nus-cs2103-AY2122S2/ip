package task;

//A variant of task
public class Todos extends Tasks{
        
    //Constructor of Todos
    public Todos(String taskName){
        super(taskName);
    }

    public Todos(String taskName, boolean completion){
        super(taskName, completion);
    }

    //Save to database format
    public String toDatabaseString(){
        return "T | " + (super.getCompletion()==true?"X":" ") + " | " + super.getName() + "\n";
    }

    //toString returning Todos
    public String toString(){
        return "[T][" + (this.getCompletion()==true?"X":" ") + "] " + super.getName();
    }
}

