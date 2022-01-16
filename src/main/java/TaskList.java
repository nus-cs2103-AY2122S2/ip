import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list= new ArrayList<Task>();

    public TaskList(){}

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

    public String markTaskDone(int index){
        try{
            if (list.size() <= 0){
                return "There are no tasks in your task list to mark";
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
        } catch (IndexOutOfBoundsException i){
            String s = String.format("Invalid number entered! No tasks marked.");
            return s;
        }
    }

    public String markTaskUndone(int index){
         try {
             if (list.size() <= 0){
                 return "There are no tasks in your task list to mark";
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
         } catch (IndexOutOfBoundsException i){
             String s = String.format("Invalid number entered! No tasks unmarked.");
             return s;
         }
    }

    public String getTaskList(){
        int i = 1;
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
