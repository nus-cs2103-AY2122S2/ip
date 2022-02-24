package saitama.tags;

import java.util.Arrays;
import java.util.HashMap;

/**
 * A list of tags for recurring tasks.
 */
public enum RecurFrequency implements Tag {
    WEEKLY("--rw"), BIWEEKLY("--rb"), DAILY("--rd"), MONTHLY("--rm");

    public static final String DAILY_LABEL = "(Daily)";
    public static final String WEEKLY_LABEL = "(Weekly)";
    public static final String BIWEEKLY_LABEL = "(Biweekly)";
    public static final String MONTHLY_LABEL = "(Monthly)";
    public static final String UNKNOWN_LABEL = "??";
    private static HashMap<String, RecurFrequency> tagMap = new HashMap<>();
    private String tag;

    static {
        Arrays.stream(RecurFrequency.values()).forEach(option -> tagMap.put(option.tag, option));
    }
    RecurFrequency(String tag) {
        this.tag = tag;
    }

    /**
     * Gets the RecursiveTag corresponding to the given String tag, if it exists. Otherwise, returns null.
     *
     * @param tag The String format of the tag
     * @return The RecursiveTag corresponding to the given String tag, if it exists. Otherwise, returns null
     */
    public static RecurFrequency get(String tag) {
        if (tagMap.containsKey(tag)) {
            return tagMap.get(tag);
        } else {
            return null;
        }
    }

    /**
     * Returns the string label of the RecurFrequency.
     *
     * @return the string label of the RecurFrequency
     */
    @Override
    public String toString() {
        return this.tag;
    }

    /**
     * Returns the label of the RecursiveTag.
     *
     * @return The label of the RecursiveTag
     */
    public String getLabel() {
        switch (this) {
        case DAILY:
            return DAILY_LABEL;
        case WEEKLY:
            return WEEKLY_LABEL;
        case BIWEEKLY:
            return BIWEEKLY_LABEL;
        case MONTHLY:
            return MONTHLY_LABEL;
        default:
            return UNKNOWN_LABEL;
        }
    }
}
