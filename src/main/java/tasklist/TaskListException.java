package tasklist;

/**
 * Represents a failure that occurred when manipulating a set of tasks.
 */
public class TaskListException extends Exception {
    private static final String ERR_MSG_PREFIX = "TaskListException: ";

    /**
     * Returns an exception which describes the reason for a failure
     * that occurred when manipulating a set of tasks.
     *
     * @param message describes the reason for the failure from manipulating a set of tasks.
     */
    public TaskListException(String message) {
        super(message);
    }

    /**
     * Represents a failure that occurred as a result of attempting to
     * perform an operation on a task that does not exist.
     */
    public static class TaskNotFoundException extends TaskListException {
        private static final String MSG = "Unable to operate on task that does not exist";

        /**
         * Returns an exception which describes the attempted operation on a
         * task that does not exist as the cause of failure.
         */
        public TaskNotFoundException() {
            super(TaskNotFoundException.MSG);
        }
    }

    /**
     * Returns the string representation of the exception with a relevant
     * prefix for easier identification of exception type.
     *
     * @return A string representation of the exception.
     */
    @Override
    public String toString() {
        return TaskListException.ERR_MSG_PREFIX + super.getMessage();
    }
}
