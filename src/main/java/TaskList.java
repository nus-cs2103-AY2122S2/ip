import java.util.*;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>(100);
    }

    private int generateNewTaskId() {
        return tasks.size() + 1; // taskId numbering starts from 1
    }

    public void addToDo(String name) {
        int taskId = generateNewTaskId();
        ToDo toDo = new ToDo(taskId, name);
        tasks.add(toDo);
    }

    public void addDeadline(String name, String by) {
        int taskId = generateNewTaskId();
        Deadline deadline = new Deadline(taskId, name, by);
        tasks.add(deadline);
    }

    public void addEvent(String name, String at) {
        int taskId = generateNewTaskId();
        Event event = new Event(taskId, name, at);
        tasks.add(event);
    }

    public void markTask(int taskId) {
        for (Task t : tasks) {
            if (t.taskId == taskId) {
                t.mark();

                break;
            }
        }
    }

    public void unMarkTask(int taskId) {
        for (Task t : tasks) {
            if (t.taskId == taskId) {
                t.unMark();

                break;
            }
        }
    }

    public void print() {
        // if there are no tasks, inform the user
        if (tasks.size() == 0) {
            System.out.println("No tasks found! (trust me, I've looked everywhere)");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.format("%d. %s\n", i + 1, tasks.get(i));
            }
        }
    }
}
