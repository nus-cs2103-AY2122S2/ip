import javax.swing.tree.ExpandVetoException;

public abstract class Task{
    protected String taskName;
    protected char done = ' ';

    public Task(){}

    public static Task newTask(String s) throws InvalidTaskDataTimeException,InvalidTaskDescriptionException,InvalidTaskTypeException{
        if (s.startsWith("todo")){
            return newToDo(s);
        }
        else if (s.startsWith("deadline")){
            return newDeadline(s);
        }
        else if (s.startsWith("event")){
            return newEvent(s);
        } else {
            throw new InvalidTaskTypeException(s);
        }
    }

    private static ToDo newToDo(String s) throws InvalidTaskDescriptionException{
        String taskName =  s.replaceFirst("todo","").strip();
        return new ToDo(taskName);
    }

    private static Event newEvent(String s) throws InvalidTaskDataTimeException,InvalidTaskDescriptionException{
        String taskName =  s.replaceFirst("event","").strip();
        return new Event(taskName);
    }

    private static Deadline newDeadline(String s) throws InvalidTaskDataTimeException,InvalidTaskDescriptionException{
        String taskName =  s.replaceFirst("deadline","").strip();
        return new Deadline(taskName);
    }

    public void markDone(){
        this.done = 'X';
    }

    public void markunDone(){
        this.done = ' ';
    }

    public String getTaskName(){
        return this.taskName;
    }

    public String toString(){
        String s = String.format("[%c][%c] %s",this.getType(),this.done,this.taskName);
        return s;
    }

    public abstract char getType();
}
