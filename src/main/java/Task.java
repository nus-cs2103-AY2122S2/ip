import javax.swing.tree.ExpandVetoException;

public abstract class Task{
    protected String taskName;
    protected char done;
    public Task(String taskName){
        this.taskName = taskName;
        this.done = ' ';
    }

    public static Task newTask(String s){
        if (s.contains("todo")){
            return newToDo(s);
        }
        else if (s.contains("deadline")){
            return newDeadline(s);
        }
        else if (s.contains("event")){
            return newEvent(s);
        } else {
            // TODO handle null case
            return null;
        }
    }

    private static ToDo newToDo(String s){
        String taskName =  s.replaceAll("todo","").strip();
        return new ToDo(taskName);
    }

    private static Event newEvent(String s){
        String taskName =  s.replaceAll("event","").strip();
        return new Event(taskName);
    }

    private static Deadline newDeadline(String s){
        String taskName =  s.replaceAll("deadline","").strip();
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
