package tasklist;

import java.util.Optional;

import tasks.Task;

/**
 * Represents a set of operations that can be performed on
 * a set of tasks.
 */
public interface TaskList {
    /**
     * Returns the total number of tasks in the task list.
     *
     * @return The total number of tasks in the task list.
     */
    int size();

    /**
     * Finds all tasks that meet the keyword search criteria in the current
     * task list.
     *
     * @param keyword the criteria to find the tasks in the current task list by.
     * @return All Task objects in the current task list that satisfies the keyword search criteria.
     * @throws TaskListException If a failure occurs while finding the tasks that
     * meet the keyword search criteria in the current task list.
     */
    Task[] find(String keyword) throws TaskListException;

    /**
     * Finds and returns a task as based on its identifier in the current task
     * list. If no task with the given identifier is found, the result returned
     * would encapsulate nothing. Otherwise, it would encapsulate the Task object
     * that is found.
     *
     * @param id the identifier of the Task object to return from the current task list.
     * @return A result that possibly contains the Task object with the given identifier.
     * @throws TaskListException If a failure occurs when finding a task with a given
     * identifier in the current task list.
     */
    Optional<Task> getById(int id) throws TaskListException;

    /**
     * Returns all the tasks from the task list.
     *
     * @return All Task objects in the current task list.
     * @throws TaskListException If a failure occurs when retrieving all tasks from
     * the task list.
     */
    Task[] get() throws TaskListException;

    /**
     * Adds a new task to the task list.
     *
     * @param taskToCreate the task to be added to the task list.
     * @throws TaskListException If a failure occurs when adding the new task to
     * the task list.
     */
    void add(Task taskToCreate) throws TaskListException;

    /**
     * Replaces the task with a given identifier in the current task list
     * with a new task.
     *
     * @param id the identifier of the task to replace in the current task list.
     * @param taskToUpdate the task that will replace the current task with a given identifier in the task list.
     * @throws TaskListException If a failure occurs when replacing the current task
     * with a given identifier in the task list with the updated task.
     */
    void update(int id, Task taskToUpdate) throws TaskListException;

    /**
     * Removes a task with a given identifier from the current task list.
     *
     * @param id the identifier of the task to be removed from the current task list.
     * @throws TaskListException If a failure occurs when removing a task with a
     * given identifier from the current task list.
     */
    void remove(int id) throws TaskListException;
}
