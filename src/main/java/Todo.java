public class Todo extends Task{
    public Todo(String taskName,boolean b) throws DukeException{
        super();
        if (taskName.strip().equals("")) throw new DukeException("Task Name cannot be empty!");
        this.taskName = taskName;
    }

    public Todo(String taskName){
        this.taskName = taskName;
    }

    public char getType(){
        return 'T';
    }
}