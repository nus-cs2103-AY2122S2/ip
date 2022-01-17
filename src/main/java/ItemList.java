import java.util.*;

public class ItemList {
    private ArrayList<String> list;

    public ItemList() {
        this.list = new ArrayList<>();
    }

    public void addItem(String s) {
        list.add(s);
    }

    public void printItems() {
        int counter = 1;
        for (String s : list) {
            System.out.print(counter + ". ");
            System.out.println(s);
            counter++;
        }
    }
}
