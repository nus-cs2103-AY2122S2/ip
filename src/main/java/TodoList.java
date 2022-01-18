import java.util.Arrays;

public class TodoList {

    private String[] items;
    private int size;

    TodoList() {
        items = new String[100];
        size = 0;
    }

    public void addItem(String item) {
        items[size++] = item;
    }

    public String showList() {
        String result = "";
        for (int i = 0; i < size; i++) {
            result = result.concat(String.format("%d. %s\n", i+1, items[i]));
        }
        return result;
     }
}
