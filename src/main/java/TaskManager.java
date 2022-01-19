import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> list= new ArrayList<Task>();

    public TaskManager(){}

    public String addTask(String s){
        try {
            Task t = Task.newTask(s);
            list.add(t);

            String str = String.format("Got it. I've added this task:\n" +
                    "\t%s\n" +
                    "Now you have %d tasks in the list.", t.toString(), list.size());
            return str;
        } catch (InvalidTaskDescriptionException i){
            return i.toString();
        } catch (InvalidTaskDataTimeException u){
            return u.toString();
        } catch (InvalidTaskTypeException t){
            return t.toString();
        }
    }

    public String deleteTask(int index){
        if (list.size() == 0){
            return "There are no tasks in your task list to delete.";
        } else {
            if (index <= 0 || index >list.size()){
                return String.format("Invalid number entered! The number entered must be between 1 and %s",list.size());
            } else {
                Task t = list.get(index-1);
                list.remove(index-1);
                String s = String.format(   "Noted. I have removed this task:\n" +
                                            "\t%s\n" +
                                            "There are now %d tasks in your task list",t,list.size());
                return s;
            }
        }
    }

    public String markTaskDone(int index){
        if (list.size() <= 0){
            return "There are no tasks in your task list to mark";
        } else {
            if (index <= 0 || index > list.size()){
                String s = String.format("Invalid number entered! No tasks marked.");
                return s;
            } else {
                Task t = list.get(index - 1);
                String s;
                if (t.done == ' ') {
                    t.markDone();
                    s = String.format("+++ Nice! I've marked this task as done:\n" +
                            "+++ %s\n", t);
                } else {
                    s = String.format("+++ This task is already marked as done:\n" +
                            "+++ %s\n", t);
                }
                return s;
            }
        }
    }

    public String markTaskUndone(int index){
        if (list.size() <= 0){
            return "There are no tasks in your task list to mark";
        } else {
            if (index <= 0 || index > list.size()) {
                String s = String.format("Invalid number entered! No tasks unmarked.");
                return s;
            } else {
                Task t = list.get(index - 1);
                String s;
                if (t.done == 'X') {
                    t.markunDone();
                    s = String.format("--- OK, I've marked this task as not done yet:\n" +
                            "--- %s\n", t);
                } else {
                    s = String.format("--- This task has not been marked as done yet:\n" +
                            "--- %s\n", t);
                }
                return s;
            }
        }
    }

    public String getTaskList(){
        int i = 1;
        if (list.size() == 0){
            String s =  "=====================================================================================\n";
            s += "Your task list is empty.\n";
            s +=        "=====================================================================================";
            return s;
        }
        String s =  "=====================================================================================\n";
        s += "Here is your task list:\n";
        for (Task t: list) {
            s+=String.format("%d.%s\n",i,t.toString());
            i++;
        }
        s +=        "=====================================================================================";
        return s;
    }

    public int size(){
        return list.size();
    }
}
