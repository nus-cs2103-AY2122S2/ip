package duke;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasksList = new ArrayList<Task>();

    public TaskList(ArrayList<String> tasksList) throws DukeException {
        for (String t : tasksList) {
            String tType = t.substring(0, 7);
            boolean tIsDone = false;
            if (tType.charAt(4) == 'X') {
                tIsDone = true;
            }
            switch (tType.charAt(1)) {
            case 'T':
                String tTodo = t.substring(7);
                this.tasksList.add(new Todo(tTodo, tIsDone));
            case 'E':
                String[] tEvent = t.substring(7).split(" - at: ");
                this.tasksList.add(new Event(tEvent[0], tIsDone,
                        LocalDate.parse(tEvent[1], DateTimeFormatter.ofPattern("MMM dd yyyy"))
                                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
            case 'D':
                String[] tDeadline = t.substring(7).split(" - by: ");
                this.tasksList.add(new Deadline(tDeadline[0], tIsDone,
                        LocalDate.parse(tDeadline[1], DateTimeFormatter.ofPattern("MMM dd yyyy"))
                                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
            }
        }
    }

    public TaskList() {
        this.tasksList = new ArrayList<Task>();
    }

    public void add(Task t) {
        this.tasksList.add(t);
    }

    public void delete(int taskId) {
        tasksList.remove(taskId);
    }

    public int size() {
        return tasksList.size();
    }

    public ArrayList<Task> getTasksList() {
        return tasksList;
    }

    public Task getTask(int taskId) {
        return tasksList.get(taskId);
    }

    @Override
    public String toString() {
        String result = "";
        int count = 1;
        result += "Here are the tasks in your list: \n";
        for (Task record : tasksList) {
            result += count + ". " + record.toString() + "\n";
            count++;
        }
        return result;
    }
}
