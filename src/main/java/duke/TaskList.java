package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<Task>();

    public TaskList(ArrayList<String> taskList) throws DukeException {
        for (String t : taskList) {
            String tType = t.substring(0, 7);
            boolean tIsDone = false;
            if (tType.charAt(4) == 'X') {
                tIsDone = true;
            }
            switch (tType.charAt(1)) {
            case 'T':
                String tTodo = t.substring(7);
                this.taskList.add(new ToDo(tTodo, tIsDone));
                break;
            case 'E':
                String[] tEvent = t.substring(7).split(" - at: ");
                this.taskList.add(new Event(tEvent[0], tIsDone,
                        LocalDate.parse(tEvent[1], DateTimeFormatter.ofPattern("MMM dd yyyy"))
                                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
                break;
            case 'D':
                String[] tDeadline = t.substring(7).split(" - by: ");
                this.taskList.add(new Deadline(tDeadline[0], tIsDone,
                        LocalDate.parse(tDeadline[1], DateTimeFormatter.ofPattern("MMM dd yyyy"))
                                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
                break;
            }
        }
    }

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public void add(Task t) {
        this.taskList.add(t);
    }

    public void delete(int taskId) {
        taskList.remove(taskId);
    }

    public int size() {
        return taskList.size();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public Task getTask(int taskId) {
        return taskList.get(taskId);
    }

    @Override
    public String toString() {
        String result = "";
        int count = 1;
        result += "Here are the tasks in your list: \n";
        for (Task record : taskList) {
            result += count + ". " + record.toString() + "\n";
            count++;
        }
        return result;
    }
}
