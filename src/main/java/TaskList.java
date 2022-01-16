import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list= new ArrayList<Task>();

    public TaskList(){

    }

    public String addTask(String s){
        Task t = Task.newTask(s);
        list.add(t);

        String str = String.format( "Got it. I've added this task:\n" +
                                    "\t%s\n" +
                                    "Now you have %d tasks in the list.\n",t.toString(),list.size());
        return str;
    }

    public String markTaskDone(int index){
        // TODO Handle index out of bounds exception
        Task t = list.get(index-1);
        t.markDone();
        String s = String.format("Nice! I've marked this task as done:\n");
        s += t.toString();
        return s;
    }

    public String markTaskUndone(int index){
        // TODO Handle index out of bounds exception
        Task t = list.get(index-1);
        t.markunDone();
        String s = String.format("OK, I've marked this task as not done yet:\n");
        s += t.toString();
        return s;
    }

    public String getTaskList(){
        int i = 1;
        String s = "Here is your task list:\n";
        for (Task t: list) {
            s+=String.format("%d.%s\n",i,t.toString());
            i++;
        }
        return s;
    }
}
