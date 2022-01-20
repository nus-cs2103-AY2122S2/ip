import java.util.ArrayList;
import java.lang.StringBuilder;

public class TrackingList {
    private ArrayList<Task> trackingList;

    public TrackingList() {
        this.trackingList = new ArrayList<>();
    }

    public String addTodo(String description) {
        ToDo toDo = new ToDo(description);
        this.trackingList.add(toDo);
        return toDo.toString();
    }

    public String addEvent(String description, String eventDate) {
        Event event = new Event(description, eventDate);
        this.trackingList.add(event);
        return event.toString();
    }

    public String addDeadline(String description, String dueDate) {
        Deadline deadline = new Deadline(description, dueDate);
        this.trackingList.add(deadline);
        return deadline.toString();
    }

    // Return the relevant Task in string form
    public String markTaskAsDone(int taskNumber) {
        Task task = this.trackingList.get(taskNumber - 1); // assume valid taskNumber
        task.markAsDone();
        return task.toString();
    }

    // Return the relevant Task in string form
    public String markTaskAsNotDone(int taskNumber) {
        Task task = this.trackingList.get(taskNumber - 1); // assume valid taskNumber
        task.markAsNotDone();
        return task.toString();
    }

    public int getNumberOfTasks() {
        return this.trackingList.size();
    }

    @Override
    public String toString() {
        if (this.trackingList.size() == 0) {
            return "Empty list!";
        }
        StringBuilder output = new StringBuilder();
        for (int i = 1; i <= this.trackingList.size(); i++) {
            output.append(String.format("%d. %s\n", i, this.trackingList.get(i - 1)));
        }
        return output.toString();
    }
}
