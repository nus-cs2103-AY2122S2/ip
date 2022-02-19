package stevie.exception.messages;

public enum TaskExceptionMessages {
    InvalidTaskTypeError("There is no such task type!"),
    InvalidTaskIndexError("Index is invalid"),
    EmptyTaskListError("There is no task in task list!"),
    EmptyUndoListError("There is nothing to undo!");
    private final String message;
    TaskExceptionMessages(String message) {
        this.message = message;
    }
    @Override
    public String toString() {
        return message;
    }
}
