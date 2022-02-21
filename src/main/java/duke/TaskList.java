package duke;

import java.util.ArrayList;

/**
 * Encapsulates a list of a {@link Task}
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * creates a TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * adds a {@link Task} to this list.
     *
     * @param task the task to be added
     */
    public void addTask(Task task) {
        assert task != null;
        tasks.add(task);
    }

    /**
     * deletes a {@link Task} to this List
     *
     * @param num the task to be deleted
     * @return the deleted task
     */
    public Task deleteTask(int num) {
        Task deletee = tasks.get(num - 1);
        tasks.remove(num - 1);
        return deletee;
    }

    /**
     * returns the string representation of the TaskList.
     *
     * @return the string representation of the TaskList.
     */
    @Override
    public String toString() {
        String wrapee = "";
        for (int i = 0; i < tasks.size(); i++) {
            wrapee = wrapee + (i + 1) + ". " + tasks.get(i) + "\n";
        }
        return wrapee;
    }

    /**
     * marks the Nth task in the list as finished, using 1 based indexing.
     *
     * @param num index of the list to mark finished, with 1 based indexing
     * @return the response to the user when the job is done
     */
    public String markFinished(int num) {
        tasks.get(num - 1).finished();
        return Response.RESPONSE_MARKDONE + "\n" + tasks.get(num - 1).toString();
    }

    /**
     * unmarks the Nth task in the list as finished, using 1 based indexing.
     *
     * @param num index of the list to unmark finished, with 1 based indexing
     * @return the response to the user when the job is done
     */
    public String unmarkFinished(int num) {
        tasks.get(num - 1).notFinished();
        return Response.RESPONSE_MARKDONE + "\n" + tasks.get(num - 1).toString();
    }

    /**
     * returns the number of tasks in the list.
     *
     * @return the number of tasks in the list
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * returns the String representation of the TaskList, to be stored locally.
     *
     * @return the String representation of the TaskList, to be stored locally.
     */
    public String toData() {
        String textData = "";
        for (int i = 0; i < tasks.size(); i++) {
            textData += tasks.get(i).toData() + "\n";
        }
        return textData;
    }

    public TaskList search(String keyword) {
        TaskList searchResult = new TaskList();
        for (int i = 0; i < this.tasks.size(); i++) {
            int index = this.tasks.get(i).toString().indexOf(keyword);
            if (index != -1) {
                searchResult.addTask(this.tasks.get(i));
            }
        }
        return searchResult;
    }

    public Task get(int i) {
        return tasks.get(i);
    }
}
