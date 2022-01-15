import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> actions = new ArrayList<Task>();

    public boolean add(String action){
         return actions.add(new Task(action));
    }

    public void print(){
        for (int i = 0; i < actions.size(); i++) {
            System.out.print(i + 1);
            System.out.print(".");
            actions.get(i).print();
        }
    }

    public void print(int i){
        actions.get(i - 1).print();
    }

    public void markComplete(int index){
        this.actions.get(index - 1).markCompleted();
    }

    public void markIncomplete(int index){
        this.actions.get(index - 1).markIncompleted();
    }
}
