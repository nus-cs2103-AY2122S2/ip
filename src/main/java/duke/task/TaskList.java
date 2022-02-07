package duke.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Stores a list of task as well as an associated set of operations.
 *
 */
public class TaskList {
    private List<Task> objectives;

    public TaskList() {
        this.objectives = new ArrayList<>();
    }

    public TaskList(List<Task> objectives) {
        this.objectives = objectives;
    }

    /**
     * Add a task to the task list.
     *
     * @param obj Task to be added to TaskList.
     */
    public void add(Task obj) {
        objectives.add(obj);
    }

    /**
     * Get a list of tasks.
     *
     * @return List of Tasks.
     */
    public List<Task> getObjectives() {
        return this.objectives;
    }

    public List<Task> getObjectives(String keyword) {
        List<Task> matches = new ArrayList<>();
        for (int i = 0; i < objectives.size(); i++) {
            if (objectives.get(i).contains(keyword)) {
                matches.add(objectives.get(i));
            }
        }
        return matches;
    }

    public List<Task> getObjectives(LocalDate keyword) {
        List<Task> matches = new ArrayList<>();
        for (int i = 0; i < objectives.size(); i++) {
            if (objectives.get(i).sameTime(keyword)) {
                matches.add(objectives.get(i));
            }
        }
        return matches;
    }

    /**
     * Return a task stored at a specified index.
     *
     * @param i index of Task stored within TaskList
     * @return Task stored at index.
     */
    public Task getTask(int i) {
        return this.objectives.get(i);
    }

    /**
     * Return number of tasks in the list.
     *
     * @return Size of the TaskList.
     */
    public Integer getSize() {
        return objectives.size();
    }

    /**
     * Set a task in the TaskList to completed or uncompleted according to index.
     *
     * @param index Index of the task to be altered.
     * @param mark Whether task should be marked or unmarked.
     */
    public void mark(Integer index, boolean mark) {
        if (mark) {
            objectives.get(index).mark();
        } else {
            objectives.get(index).unmarked();
        }
    }

    /**
     * Remove a task in the TaskList according to index.
     *
     * @param index Index of the task to be removed.
     */
    public void delete(Integer index) {
        this.objectives.remove((int) index);
    }

    /**
     * Get a serialized list of tasks.
     *
     * @return List of serialized string.
     */
    public List<String> serializedList() {
        List<String> encodedList = new ArrayList<>();
        for (Task task : objectives) {
            encodedList.add(task.serialize());
        }
        assert encodedList.size() == objectives.size();
        return encodedList;
    }
}
