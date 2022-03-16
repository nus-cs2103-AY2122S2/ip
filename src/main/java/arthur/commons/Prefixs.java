package arthur.commons;

/**
 * Stores the commonly used prefix in Arthur computation.
 */
public class Prefixs {
    // For location to split in the given string
    public static final String DEADLINE_SPLIT_LOC = "/by ";
    public static final String EVENT_SPLIT_LOC = "/at ";
    public static final String TASK_INFO_SPLIT_LOC = " >> ";

    // String to be replaced when converting storage info to user info
    public static final String DEADLINE_TO_REPLACE = "\\(By:";
    public static final String DEADLINE_REPLACE_WITH = "/by";
    public static final String EVENT_TO_REPLACE = "\\(At:";
    public static final String EVENT_REPLACE_WITH = "/at";
}
