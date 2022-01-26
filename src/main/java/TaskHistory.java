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

    void addToDo(int mark, String description) {
        ToDos tempToDo = new ToDos(mark, description);
        record.add(tempToDo);
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

    void addDeadline(int mark, String description, String timeFrame) {
        Deadlines tempDeadline = new Deadlines(mark, description, timeFrame);
        record.add(tempDeadline);
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

    void addEvent(int mark, String description, String timeFrame) {
        Event tempEvent = new Event(mark, description, timeFrame);
        record.add(tempEvent);
    }

    /**
     * Returns string containing all the tasks in stored in DukeLCH.
     * If there is no tasks stored, "There has been no recorded user
     * input!" is returned.
     *
     * @return string containing all the tasks stored in DukeLCH.
     */
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
                System.out.println("Error occurred while processing " + nextTask.getTask()); // For some reason the task type is unknown
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

    void deleteTask(int index) {
        StringBuilder description = new StringBuilder();
        Task temp = record.remove(index);
        if (temp instanceof ToDos) {
            ToDos tempToDos = (ToDos) temp;
            description.append(tempToDos.getToDo());
        } else if (temp instanceof Deadlines) {
            Deadlines tempDeadlines = (Deadlines) temp;
            description.append(tempDeadlines.getDeadline());
        } else if (temp instanceof Event) {
            Event tempEvent = (Event) temp;
            description.append(tempEvent.getEvent());
        } else {
            System.out.println("Error occurred while deleting " + temp.getTask()); // For some reason the task type is unknown
        }
        String msg = "_______________________________________________________\n"
                + "Understood, removing this task now:\n"
                + "    " + description
                + "Now you have " + record.size() + " tasks in our records.\n"
                + "_______________________________________________________\n";
        System.out.println(msg);
    }
}
