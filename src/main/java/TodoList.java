import java.util.Arrays;

public class TodoList {

    private final Task[] items;
    private int size;

    TodoList() {
        items = new Task[100];
        size = 0;
    }

    public String markItem(int index) {
        items[index].mark();
        return items[index].toString();
    }

    public String unmarkItem(int index) {
        items[index].unmark();
        return items[index].toString();
    }

    public String addTodo(String name) {
        items[size++] = new TodoTask(name);
        return items[size-1].toString();
    }

    public String addDeadline(String name, String date) {
        items[size++] = new DeadlineTask(name, date);
        return items[size-1].toString();
    }

    public String addEvent(String name, String date) {
        items[size++] = new EventTask(name, date);
        return items[size-1].toString();
    }

    public String listCount() {
        return String.format("Now you have %d tasks in the list.", size);
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < size; i++) {
            result = result.concat(String.format("%d.%s\n", i + 1, items[i]));
        }
        return result;
    }



}
