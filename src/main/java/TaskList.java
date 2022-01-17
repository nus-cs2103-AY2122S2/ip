import java.util.*;

public class TaskList {
    protected ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void addItem(String s) {
        list.add(new Task(s));
    }

    public void addTodo(String s) {
        list.add(new Todo(s));
    }

    public void addEvent(String s, String time) {
        list.add(new Event(s,time));
    }

    public void addDeadline(String s, String time) {
        list.add(new Deadline(s,time));
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

    public String getLast() {
        return list.get(list.size() - 1).toString();
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

    public int size() {
        return list.size();
    }
}
