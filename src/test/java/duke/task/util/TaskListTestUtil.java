package duke.task.util;

public class TaskListTestUtil {
    public static final String MAKE_TODO_INPUT_VALID = "todo homework";
    public static final String MAKE_DEADLINE_INPUT_VALID = "deadline assignment /by 05/03/2022 2359";
    public static final String MAKE_EVENT_INPUT_VALID = "event wedding /at 11/12/2022 1800";
    public static final String DELETE_FIRST_TASK_VALID = "delete 1";
    public static final String DELETE_FARAWAY_TASK_VALID = "delete 999";
    public static final String MAKE_TODO_INPUT_INVALID = "todo";
    public static final String MAKE_DEADLINE_EMPTY_DESC_INVALID = "deadline /by 12/12/2022 1400";
    public static final String MAKE_DEADLINE_EMPTY_METAINFO_INVALID = "deadline";
    public static final String MAKE_EVENT_EMPTY_DESC_INVALID = "event /at 11/12/2022 1800";
    public static final String MAKE_EVENT_EMPTY_METAINFO_INVALID = "event";
    public static final String MAKE_DEADLINE_SPACE_DESC_INVALID = "deadline    /by 12/12/2022 1400";
    public static final String MAKE_EVENT_SPACE_DESC_INVALID = "event        /at 11/12/2022 1800";
}
