import java.util.ArrayList; // Imported ArrayList class
import java.util.Iterator; // Imported Iterator class
import java.lang.StringBuilder; // Imported StringBuilder class

public class InputHistory {
    private final ArrayList<String> record = new ArrayList<>(100); // ArrayList of size 100 by default

    public InputHistory() { //Empty Constructor
    }

    void addTo(String input) {
        record.add(input);
    }

    String printAll() {
        Iterator<String> iterator = record.iterator();
        int count = 1;
        StringBuilder result = new StringBuilder();
        while (iterator.hasNext()) {
            result.append(count).append(". ").append(iterator.next()).append("\n");
            count++;
        }
        if (result.length() == 0) {
            return "There has been no recorded user input!\n";
        } else {
            return result.toString();
        }
    }
}
