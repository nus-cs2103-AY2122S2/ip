package duke.common;

import duke.DukeException;

public enum TaskType {
    TASK, EVENT, DEADLINE, TODO, INVALID;

    /**
     * Parses user input to a sort type.
     *
     * @param userInput The user input.
     * @return A sort type enum.
     */
    public static TaskType parseTaskType(String userInput) {
        switch (userInput) {
        case "event":
            return EVENT;
        case "deadline":
            return DEADLINE;
        case "todo":
            return TODO;
        case "task":
            return TASK;
        default:
            return INVALID;
        }
    }

    public String getTaskTypeSymbol() throws DukeException {
        switch (this) {
        case TODO:
            return "T";
        case EVENT:
            return "E";
        case DEADLINE:
            return "D";
        default:
            throw new DukeException(Messages.MESSAGE_ERROR_INVALID_TASK_TYPE);
        }
    }
}
