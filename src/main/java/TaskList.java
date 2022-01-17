import java.util.*;

public class TaskList {
    protected ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void addItem(String s) {
        list.add(new Task(s));
    }

    public void markItemDone(int index) {
        Task t = list.get(index);
        t.markAsDone();
        System.out.println("Task done! \\(n_n)/");
        System.out.println("\t" + t);
    }

    public void markItemUndone(int index) {
        Task t = list.get(index);
        t.markUndone();
        System.out.println("Task not done =(");
        System.out.println("\t" + t);
    }

    public void printItems() {
        if (list.isEmpty()) {
            System.out.println("There are no tasks on your list :O");
        }
        else {
            System.out.println("Here are the tasks on your list :O");
            int counter = 1;
            for (Task t : list) {
                System.out.print(counter + ". ");
                System.out.println(t);
                counter++;
            }
        }
    }
}
