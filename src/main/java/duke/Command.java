package duke;

/**
 * Commands recognised by the application
 */
public enum Command {
    /**
     * Todo task
     * User wants to create a task of type Todo
     */
    TODO,
    /**
     * Event Task
     * User wants to create a task of type Event
     */
    EVENT,
    /**
     * Deadline Task
     * User wants to create a task of type Deadline
     */
    DEADLINE,
    /**
     * Mark as complete
     * User wants to mark a task as complete
     */
    MARK,
    /**
     * Mark as incomplete
     * User wants to mark task as incomplete
     */
    UNMARK,
    /**
     * Bye command
     * User wants to end session
     */
    BYE,
    /**
     * List tasks
     * User wants to list tasks that they have added
     */
    LIST,
    /**
     * Delete task
     * User wants to delete a task
     */
    DELETE,
    /**
     * Sort Tasks
     * User wants to sort tasks
     */
    SORT,
    /** Find task
     * User wants to find a task
     */
    FIND

}
