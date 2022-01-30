package duke;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> todo;

    public TaskList() {
        this.todo = new ArrayList<>();
    }

    public void addTask(Task task) {
        todo.add(task);
    }

    public Task deleteTask(int num) {
        Task deletee = todo.get(num - 1);
        todo.remove(num - 1);
        return deletee;
    }

    @Override
    public String toString() {
        String wrapee = "";
        for (int i = 0; i < todo.size(); i++) {
            wrapee = wrapee + (i + 1) + ". " + todo.get(i) + "\n";
        }
        return wrapee;
    }

    public String markFinished(int num) {
        todo.get(num - 1).finished();
        return Response.RESPONSE_MARKDONE + "\n" + todo.get(num - 1).toString();
    }

    public String unmarkFinished(int num) {
        todo.get(num - 1).notFinished();
        return Response.RESPONSE_MARKDONE + "\n" + todo.get(num - 1).toString();
    }

    public int size() {
        return this.todo.size();
    }

    public String toData() {
        String txtdata = "";
        for (int i = 0; i < todo.size(); i++) {
            txtdata += todo.get(i).toData() + "\n";
        }
        return txtdata;
    }
}
