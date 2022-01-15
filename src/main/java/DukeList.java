import java.util.ArrayList;

public class DukeList {
    private ArrayList<String> items = new ArrayList<>();

    //Add items
    public void add(String val) {
        String line = "____________________________________________________________";
        items.add(val);
        System.out.println(line);
        System.out.println("Added: " + val);
        System.out.println(line);
    }

    //show items in list
    public void printItems() {
        String line = "____________________________________________________________";
        System.out.println(line);
        for (int i = 0; i < items.size(); i ++) {
            System.out.println((i + 1) + ". " + items.get(i));
        }
        System.out.println(line);
    }
}
