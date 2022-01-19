import java.util.ArrayList;

public class Calendar {
    private final ArrayList<Task> calendar;

    Calendar() {
        this.calendar = new ArrayList<Task>();
    }

    void add(Task newTask) {
        calendar.add(newTask);
    }

    void remove(int index) {
        calendar.remove(index - 1);
    }

    int size() {
        return this.calendar.size();
    }

    Task get(int number) {
        return this.calendar.get(number - 1);
    }

    @Override
    public String toString() {
        if (calendar.size() == 0) {
            return "No items in To-Do List as of now :(";
        } else {
            String product = "";
            for (int i = 0; i < this.calendar.size() - 1; i++) {
                product += String.format("%s: %s\n", i + 1, this.calendar.get(i));
            }
            product += String.format("%s: %s", this.calendar.size(), this.calendar.get(this.calendar.size() - 1));

            return product;
        }

    }

}
