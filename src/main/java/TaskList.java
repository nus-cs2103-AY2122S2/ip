import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list= new ArrayList<Task>();

    public TaskList(){

    }

    public String addTask(String s){
        Task t = new Task(s);
        list.add(t);
        String str = String.format("added: %s",t.getTaskName());
        return str;
    }

    public String markTaskDone(int index){
        // TODO Handle index out of bounds exception
        Task t = list.get(index-1);
        t.markDone();
        String s = String.format("Nice! I've marked this task as done:\n");
        s += t.reply();
        return s;
    }

    public String markTaskUndone(int index){
        // TODO Handle index out of bounds exception
        Task t = list.get(index-1);
        t.markunDone();
        String s = String.format("OK, I've marked this task as not done yet:\n");
        s += t.reply();
        return s;
    }

    public String getTaskList(){
        int i = 1;
        String s = "Here is your task list:\n";
        for (Task t: list) {
            s+=String.format("%d.%s\n",i,t.reply());
            i++;
        }
        return s;
    }
}
