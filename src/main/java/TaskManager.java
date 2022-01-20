import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> list= new ArrayList<Task>();

    public TaskManager(){}

    public boolean addTask(String s){
        try {
            Task t = Task.newTask(s);
            list.add(t);

            String str = String.format("Got it. I've added this task:\n" +
                    "\t%s\n" +
                    "Now you have %d tasks in the list.", t.toString(), list.size());
            return true;
        } catch (InvalidTaskDescriptionException i){
            System.out.println(i.toString());
            return false;
        } catch (InvalidTaskDataTimeException u){
            System.out.println(u.toString());
            return false;
        } catch (InvalidTaskTypeException t){
            System.out.println(t.toString());
            return false;
        }
    }
    public void addTask(Task t){
        this.list.add(t);
    }

    public boolean deleteTask(int index){
        if (list.size() == 0){
            System.out.println("There are no tasks in your task list to delete.");
            return false;
        } else {
            if (index <= 0 || index >list.size()){
                System.out.println(String.format("Invalid number entered! The number entered must be between 1 and %s",list.size()));
                return false;
            } else {
                Task t = list.get(index-1);
                list.remove(index-1);
                String s = String.format(   "Noted. I have removed this task:\n" +
                                            "\t%s\n" +
                                            "There are now %d tasks in your task list",t,list.size());
                System.out.println(s);
                return true;
            }
        }
    }

    public boolean markTaskDone(int index){
        if (list.size() <= 0){
            System.out.println("There are no tasks in your task list to mark");
            return false;
        } else {
            if (index <= 0 || index > list.size()){
                System.out.println(String.format("Invalid number entered! No tasks marked."));
                return false;
            } else {
                Task t = list.get(index - 1);
                if (t.done == ' ') {
                    t.markDone();
                    System.out.println(String.format("+++ Nice! I've marked this task as done:\n" +
                            "+++ %s\n", t));
                    return true;
                } else {
                    System.out.println(String.format("    This task is already marked as done:\n" +
                            "    %s\n", t));
                    return false;
                }
            }
        }
    }

    public boolean markTaskUndone(int index){
        if (list.size() <= 0){
            System.out.println("There are no tasks in your task list to mark");
            return false;
        } else {
            if (index <= 0 || index > list.size()) {
                System.out.println("Invalid number entered! No tasks unmarked.");
                return false;
            } else {
                Task t = list.get(index - 1);
                if (t.done == 'X') {
                    t.markunDone();
                    System.out.println(String.format("--- OK, I've marked this task as not done yet:\n" +
                            "--- %s\n", t));
                    return true;
                } else {
                    System.out.println(String.format("    This task has not been marked as done yet:\n" +
                            "    %s\n", t));
                    return false;
                }
            }
        }
    }

    public String printTaskList(){
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

    public ArrayList<Task> getTaskList(){
        return this.list;
    }

    public int size(){
        return list.size();
    }
}
