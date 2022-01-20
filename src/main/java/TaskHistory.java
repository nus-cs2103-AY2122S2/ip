import java.lang.StringBuilder; // Imported StringBuilder class
import java.util.ArrayList; // Imported ArrayList class

public class TaskHistory {
    private final ArrayList<Task> record = new ArrayList<>(100); // ArrayList of size 100 by default

    public TaskHistory() { //Empty Constructor
    }

    void addToDo(String description) {
        ToDos tempToDo = new ToDos(description);
        record.add(tempToDo);
        String msg = "_______________________________________________________\n"
                + "Understood, adding this task now:\n"
                + "    " + tempToDo.getToDo()
                + "Currently you have " + record.size() + " tasks in our records.\n"
                + "_______________________________________________________\n";
        System.out.println(msg);
    }

    void addDeadline(String description, String timeFrame) {
        Deadlines tempDeadline = new Deadlines(description, timeFrame);
        record.add(tempDeadline);
        String msg = "_______________________________________________________\n"
                + "Understood, adding this task now:\n"
                + "    " + tempDeadline.getDeadline()
                + "Currently you have " + record.size() + " tasks in our records.\n"
                + "_______________________________________________________\n";
        System.out.println(msg);
    }

    void addEvent(String description, String timeFrame) {
        Event tempEvent = new Event(description, timeFrame);
        record.add(tempEvent);
        String msg = "_______________________________________________________\n"
                + "Understood, adding this task now:\n"
                + "    " + tempEvent.getEvent()
                + "Currently you have " + record.size() + " tasks in our records.\n"
                + "_______________________________________________________\n";
        System.out.println(msg);
    }

    String printAll() {
        int count = 1;
        StringBuilder result = new StringBuilder();
        for (Task nextTask : record) {
            if (nextTask instanceof ToDos) {
                ToDos temp = (ToDos) nextTask;
                result.append(count).append(".").append(temp.getToDo());
            } else if (nextTask instanceof Deadlines) {
                Deadlines temp = (Deadlines) nextTask;
                result.append(count).append(".").append(temp.getDeadline());
            } else if (nextTask instanceof Event) {
                Event temp = (Event) nextTask;
                result.append(count).append(".").append(temp.getEvent());
            } else {
                System.out.println("Error occurred while processing " + nextTask.getTask()); // Temporary error handler
            }
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
