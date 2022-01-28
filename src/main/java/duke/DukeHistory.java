package duke;

import java.lang.StringBuilder;
import java.util.ArrayList;

public class DukeHistory {
    private final ArrayList<Task> records = new ArrayList<>(100); // ArrayList of size 100 by default

    public DukeHistory() { //Empty Constructor
    }

    public void addToDo(String description) {
        ToDos tempToDo = new ToDos(description);
        records.add(tempToDo);
        String msg = "_______________________________________________________\n"
                + "Understood, adding this task now:\n"
                + "    " + tempToDo.getToDo()
                + "Currently you have " + records.size() + " tasks in our records.\n"
                + "_______________________________________________________\n";
        System.out.println(msg);
    }

    public void addToDo(int mark, String description) {
        ToDos tempToDo = new ToDos(mark, description);
        records.add(tempToDo);
    }

    public void addDeadline(String description, String date, String time) {
        Deadlines tempDeadline = new Deadlines(description, date, time);
        records.add(tempDeadline);
        String msg = "_______________________________________________________\n"
                + "Understood, adding this task now:\n"
                + "    " + tempDeadline.getDeadline()
                + "Currently you have " + records.size() + " tasks in our records.\n"
                + "_______________________________________________________\n";
        System.out.println(msg);
    }

    public void addDeadline(int mark, String description, String date, String time) {
        Deadlines tempDeadline = new Deadlines(mark, description, date, time);
        records.add(tempDeadline);
    }

    public void addEvent(String description, String date, String time) {
        Event tempEvent = new Event(description, date, time);
        records.add(tempEvent);
        String msg = "_______________________________________________________\n"
                + "Understood, adding this task now:\n"
                + "    " + tempEvent.getEvent()
                + "Currently you have " + records.size() + " tasks in our records.\n"
                + "_______________________________________________________\n";
        System.out.println(msg);
    }

    public void addEvent(int mark, String description, String date, String time) {
        Event tempEvent = new Event(mark, description, date, time);
        records.add(tempEvent);
    }

    /**
     * Returns string containing all the tasks in stored in DukeLCH.
     * If there is no tasks stored, "There has been no recorded user
     * input!" is returned.
     *
     * @return string containing all the tasks stored in DukeLCH.
     */
    public String printAll() {
        int count = 1;
        StringBuilder result = new StringBuilder();
        for (Task nextTask : records) {
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
                System.out.println("Error occurred while processing " + nextTask.getTask());
            }
            count++;
        }
        if (result.length() == 0) {
            return "There has been no recorded user input!\n";
        } else {
            return result.toString();
        }
    }

    public Task getTask(int index) {
        return records.get(index);
    }

    public void deleteTask(int index) {
        StringBuilder description = new StringBuilder();
        Task temp = records.remove(index);
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
            System.out.println("Error occurred while deleting " + temp.getTask());
        }
        String msg = "_______________________________________________________\n"
                + "Understood, removing this task now:\n"
                + "    " + description
                + "Now you have " + records.size() + " tasks in our records.\n"
                + "_______________________________________________________\n";
        System.out.println(msg);
    }

    String formatRecord() {
        StringBuilder s = new StringBuilder();
        for (Task temp : records) {
            if (temp instanceof ToDos) {
                ToDos tempToDos = (ToDos) temp;
                s.append(tempToDos.getFormattedText()).append("\n");
            } else if (temp instanceof Deadlines) {
                Deadlines tempDeadlines = (Deadlines) temp;
                s.append(tempDeadlines.getFormattedText()).append("\n");
            } else if (temp instanceof Event) {
                Event tempEvent = (Event) temp;
                s.append(tempEvent.getFormattedText()).append("\n");
            } else {
                System.out.println("Error occurred while deleting " + temp.getTask());
            }
        }
        return s.toString();
    }

    public int getLength() {
        return records.size();
    }
}
