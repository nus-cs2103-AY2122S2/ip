import java.util.*;

public class TaskList {
    protected ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
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

    public String getLast() { return list.get(list.size() - 1).toString(); }

    public int size() { return list.size(); }

    public String markItemDone(int index) throws InvalidActionException {
        Task t = list.get(index);
        t.markAsDone();
        return "Task done! \\(n_n)/\n " + t.toString();
    }

    public String markItemUndone(int index) throws InvalidActionException {
        Task t = list.get(index);
        t.markUndone();
        return "Task not done =(\n " + t.toString();
    }

    public String deleteItem(int index) {
        Task t = list.remove(index);
        return "deleted this item O_O:\n  " + t + "\nNow there are " + list.size() + " tasks on the list x)";
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
