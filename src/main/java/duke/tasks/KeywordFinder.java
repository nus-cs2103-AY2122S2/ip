package duke.tasks;

/**
 * Implements method to find tasks that match a keyword.
 */
public interface KeywordFinder {
    /**
     * Checks if object matches specified keyword.
     *
     * @param keyword the keyword
     * @return whether or not object matches keyword
     */
    boolean matches(String keyword);
}
