import javax.swing.tree.ExpandVetoException;

public class Task{
    protected String taskName;
    protected char done = ' ';
    protected String date="empty";

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

    public static Task parse(String s){
        // <type>\t<done>\t<name>\t<date>
        char type;
        char done;
        String name;
        String date;
        Task t;

        try {
            String[] str = s.split("\t");
            type = str[0].toCharArray()[0];
            done = str[1].toCharArray()[0];
            name = str[2];
            date = str[3];

            t = new Task();
        } catch (IndexOutOfBoundsException i){
            return null;
        }

        try {
            if (type=='T') {
                t = newToDo(name);
            } else if (type=='D'){

                t = newDeadline(name + " /by " + date);
            } else if (type=='E'){

                t = newEvent(name + " /at " + date);
            }

            if (done=='X'){
                t.markDone();
            }
            return t;
        } catch (InvalidTaskDescriptionException i){
            System.out.println(i.toString());
            return null;
        } catch (InvalidTaskDataTimeException d){
            System.out.printf(d.toString());
            return null;
        }
    }

    public void markDone(){
        this.done = 'X';
    }
    public void markunDone(){
        this.done = ' ';
    }

    public char getDone(){
        return this.done;
    }
    public String getTaskName(){
        return this.taskName;
    }
    public String getDate(){
        return this.date;
    }

    public String toString(){
        String s = String.format("[%c][%c] %s",this.getType(),this.done,this.taskName);
        return s;
    }

    public char getType(){
        return ' ';
    }
}
