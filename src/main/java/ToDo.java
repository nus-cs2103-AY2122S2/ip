public class ToDo extends Task{
    public ToDo(String taskName) throws InvalidTaskDescriptionException{
        super();
        if (taskName.strip().equals("")) throw new InvalidTaskDescriptionException("To Do");
        this.taskName = taskName;

    }

    public char getType(){
        return 'T';
    }
}
