import java.util.ArrayList;

public class TaskList {
    private final ArrayList<String> itemList = new ArrayList<>(0);

    public TaskList() {}

    public void addItem(String key) {
        this.itemList.add(key);
    }

    public String printList() {
        StringBuilder initList = new StringBuilder();

        for (int i = 1; i < itemList.size() + 1; i++) {
            initList.append(i + ". ");
            initList.append(itemList.get(i - 1));
            initList.append("\n");
        }

        return initList.toString();
    }
}
