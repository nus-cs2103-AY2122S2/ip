import java.util.ArrayList; // Imported ArrayList class
import java.util.Iterator; // Imported Iterator class

public class InputHistory {
    ArrayList<String> record = new ArrayList<String>(100); // ArrayList of size 100 by default

    public InputHistory() { //Empty Constructor
    }

    void addTo(String input) {
        record.add(input);
    }

    void printAll() {
        Iterator<String> iterator = record.iterator();
        int count = 1;
        while (iterator.hasNext()) {
            System.out.println(count + ". " + iterator.next() + "\n");
        }
    }
}
