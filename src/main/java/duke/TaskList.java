package duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> tasks;

    /**
     * TaskList constructor when there are tasks in file
     *
     * @param tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * TaskList constructor when there are no tasks in file
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>(100);
    }

    /**
     * Gives the number of tasks in the list
     *
     * @return the number of tasks in the list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Add task to task list
     *
     * @param t
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Delete task from task list
     *
     * @param index
     * @return deleted task
     */
    public Task deleteTask(int index) {
        Task t = tasks.get(index);
        tasks.remove(index);
        return t;
    }

    /**
     * Mark task as done
     *
     * @param index
     * @return task marked as done
     */
    public Task markTask(int index) {
        Task t = tasks.get(index);
        t.markAsDone();
        return t;
    }

    /**
     * Unmark task
     * @param index
     * @return tasked marked as undone
     */
    public Task unmarkTask(int index) {
        Task t = tasks.get(index);
        t.markAsNotDone();
        return t;
    }


    /**
     * Get task based on index
     *
     * @param index
     * @return task based on index
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Find task in list
     * @param key
     * @return
     */
    public Task[] find(String key) {
        assert tasks != null : "tasklist should not be null";
        Task[] t = new Task[100];
        int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).description.contains(key)) {
                t[count] = tasks.get(i);
                count++;
            }
        }
        return t;
    }

    /**
     *
     * @param date task due by
     * @return list of task that are due soon
     */
    public Task[] reminder(String date) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime formattedDate = LocalDateTime.parse(date, format);
        Task[] t = new Task[100];
        int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            boolean isBeforeDueDate = tasks.get(i).getDateTime().isBefore(formattedDate);
            boolean isAfterCurrentDate = tasks.get(i).getDateTime().isAfter(LocalDateTime.now());
            if (isBeforeDueDate && isAfterCurrentDate) {
                t[count] = tasks.get(i);
                count++;
            }
        }
        return t;
    }

    /**
     *Overrides toString method
     *
     * @return tasks in task list
     */
    public String toString() {
        String taskList = "";
        for (int i = 0; i < tasks.size(); i++) {
            int count = i + 1;
            taskList += count + "." + tasks.get(i) + "\n";
        }
        return taskList;
    }
}
