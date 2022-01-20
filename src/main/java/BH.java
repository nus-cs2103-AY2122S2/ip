import java.util.*;
public class BH {
    private ArrayList<String> list;

    BH() {
        this.list = new ArrayList<String>();
    }

    void addToList(String input) {
        this.list.add(input);
    }

    String getList() {
        String s = "";
        for (int i = 0; i < this.list.size(); i++) {
            s = s + (i + 1) + ". " + list.get(i) + "\n";
        }
        return s;
    }
}
