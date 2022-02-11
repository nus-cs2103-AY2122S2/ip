package tasklist;

public class TaskListException extends Exception {
    private static final String ERR_MSG_PREFIX = "TaskListException: ";

    public TaskListException(String message) {
        super(message);
    }

    public static class TaskNotFoundException extends TaskListException {
        private static final String MSG = "Unable to operate on task that does not exist";

        public TaskNotFoundException() {
            super(TaskNotFoundException.MSG);
        }
    }

    @Override
    public String toString() {
        return TaskListException.ERR_MSG_PREFIX + super.getMessage();
    }
}
