import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> list;

    protected TaskList() {
        list = new ArrayList<>();
    }

    public void addTodo(Todo t) {
        list.add(t);
    }

    public void addEvent(Event e) {
        list.add(e);
    }

    public void addDeadline(Deadline d) {
        list.add(d);
    }

    protected Task getLast() {
        return list.get(list.size() - 1);
    }

    protected int size() {
        return list.size();
    }

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
