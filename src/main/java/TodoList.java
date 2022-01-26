import java.util.ArrayList;

public class TodoList {

    private final ArrayList<Task> items;

    TodoList() {
        items = new ArrayList<>();
    }

    public String deleteItem(int index) {
        Task item = items.get(index);
        items.remove(index);
        return item.toString();
    }

    public String markItem(int index) {
        items.get(index).mark();
        return items.get(index).toString();
    }

    public String unmarkItem(int index) {
        items.get(index).unmark();
        return items.get(index).toString();
    }

    public String addTodo(String name) {
        Task item = new TodoTask(name);
        items.add(item);
        return item.toString();
    }

    public String addDeadline(String name, String date) throws DukeException {
        Task item = new DeadlineTask(name, date);
        items.add(item);
        return item.toString();
    }

    public String addEvent(String name, String date) throws DukeException {
        Task item = new EventTask(name, date);
        items.add(item);
        return item.toString();
    }

    public String listCount() {
        return String.format("Now you have %d tasks in the list.", items.size());
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < items.size(); i++) {
            result = result.concat(String.format("%d.%s\n", i + 1, items.get(i)));
        }
        return result;
    }



}
