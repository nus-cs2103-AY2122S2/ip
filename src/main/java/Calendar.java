import java.util.ArrayList;

public class Calendar {
    private final ArrayList<Task> calendar;

    Calendar() {
        this.calendar = new ArrayList<Task>();
    }

    void add(Task newTask) {
        calendar.add(newTask);
    }

    @Override
    public String toString() {
        if (calendar.size() == 0) {
            return "No items in To-Do List as of now :(";
        } else {
            String product = "";
            int count = 1;
            for (Task task: this.calendar) {
                product += String.format("%s: %s\n", count++, task);
            }
            product.substring(0, product.length() - 1);
            return product;
        }

    }
}
