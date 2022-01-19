import java.util.ArrayList; // Imported ArrayList class
import java.util.Iterator; // Imported Iterator class
import java.lang.StringBuilder; // Imported StringBuilder class

public class TaskHistory {
    private final ArrayList<Task> record = new ArrayList<>(100); // ArrayList of size 100 by default

    public TaskHistory() { //Empty Constructor
    }

    void addTo(String input) {
        record.add(new Task(input));
    }

    void addToDo(String input) {
        record.add(new ToDos(input));
    }

    String printAll() {
        Iterator<Task> iterator = record.iterator();
        int count = 1;
        StringBuilder result = new StringBuilder();
        while (iterator.hasNext()) {
            result.append(count).append(".").append(iterator.next().getDescription());
            count++;
        }
        if (result.length() == 0) {
            return "There has been no recorded user input!\n";
        } else {
            return result.toString();
        }
    }

    Task getTask(int index) {
        return record.get(index);
    }
}
