package task;

import java.util.ArrayList;

/**
 * Class to store tasks in a list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Get all tasks that are stored in this instance.
     * @return ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Getter function for tasks at index.
     * @param index index to retrieve.
     * @return task at specified index.
     * @throws IndexOutOfBoundsException
     */
    public Task getTaskAtIndex(int index) throws IndexOutOfBoundsException {
        return this.tasks.get(index);
    }

    /**
     * Marks task as done.
     * @param index index of task to be marked as done.
     * @return response message after this task is marked.
     * @throws IndexOutOfBoundsException
     */
    public String markTaskAsDone(int index) throws IndexOutOfBoundsException {
        this.tasks.get(index).markAsDone();
        return String.format("%s \n %s \n",
                "Nice! I've marked this task as done:",
                tasks.get(index));
    }

    /**
     * Marks task as not done.
     * @param index index of task to be unmarked as done.
     * @return response message after this task is unmarked.
     * @throws IndexOutOfBoundsException
     */
    public String unmarkTaskAsDone(int index) throws IndexOutOfBoundsException {
        this.tasks.get(index).unmarkAsDone();
        return String.format("%s \n %s \n",
                "Nice! I've unmarked this task as done:",
                tasks.get(index));
    }

    /**
     * Deletes task from the list.
     * @param index index of task to be deleted.
     * @return response message after this task is deleted.
     * @throws IndexOutOfBoundsException
     */
    public String deleteTaskAtIndex(int index) throws IndexOutOfBoundsException {
        Task deleted = this.tasks.get(index);
        System.out.println("Noted. I've removed this task:");
        System.out.println(deleted);
        this.tasks.remove(index);
        return String.format("%s \n %s \n %s \n",
                "Noted. I've removed this task:",
                deleted.toString(),
                String.format("Now you have %s tasks in the list.", this.tasks.size()));

    }

    /**
     * Adds a new task to the list.
     * @param task task to be added.
     * @return response message after the task is added.
     */
    public String addTask(Task task) {
        this.tasks.add(task);
        return String.format("%s \n%s \n%s",
                "Got it. I've added this task:",
                tasks.get(tasks.size() - 1).toString(),
                String.format("Now you have %s tasks in the list.", tasks.size()));
    }

    /**
     * Add specified task to the list, but returns no message.
     * @param task
     */
    public void addTaskNoMessage(Task task) {
        this.tasks.add(task);
    }

    @Override
    public String toString() {
        if (this.tasks.isEmpty()) {
            return "You don't have any task at the moment.";
        } else {
            StringBuilder msg = new StringBuilder();
            msg.append("Here are your current tasks:");
            for (int i = 0; i < this.tasks.size(); i++) {
                msg.append("\n");
                msg.append(i + 1);
                msg.append(". ");
                msg.append(this.tasks.get(i)
                        .toString());
            }
            return msg.toString();
        }
    }
}
