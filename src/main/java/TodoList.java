import java.util.Arrays;

public class TodoList {

    private final Task[] items;
    private int size;

    TodoList() {
        items = new Task[100];
        size = 0;
    }

    public void addItem(String item) {
        items[size++] = new Task(item);
    }

    public String markItem(int index) {
        items[index].mark();
        return items[index].toString();
    }

    public String unmarkItem(int index) {
        items[index].unmark();
        return items[index].toString();
    }

    public String showList() {
        String result = "";
        for (int i = 0; i < size; i++) {
            result = result.concat(String.format("%d.%s\n", i+1, items[i]));
        }
        return result;
     }
}
