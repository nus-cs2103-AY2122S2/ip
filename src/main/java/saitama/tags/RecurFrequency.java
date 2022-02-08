package saitama.tags;

import java.util.Arrays;
import java.util.HashMap;

/**
 * A list of tags for recurring tasks.
 */
public enum RecurFrequency implements Tag {
    WEEKLY("--rw"), BIWEEKLY("--rb"), DAILY("--rd"), MONTHLY("--rm");

    private static HashMap<String, RecurFrequency> tagMap = new HashMap<>();
    private String tag;

    static {
        Arrays.stream(RecurFrequency.values()).forEach(option -> tagMap.put(option.tag, option));
    }
    RecurFrequency(String tag) {
        this.tag = tag;
    }

    /**
     * Gets the RecursiveTag corresponding to the given tag, if it exists. Otherwise, returns null.
     *
     * @param tag
     * @return The RecursiveTag corresponding to the given tag, if it exists. Otherwise, returns null.
     */
    public static RecurFrequency get(String tag) {
        if (tagMap.containsKey(tag)) {
            return tagMap.get(tag);
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return this.tag;
    }

    /**
     * Returns the label of the RecursiveTag.
     *
     * @return The label of the RecursiveTag.
     */
    public String getLabel() {
        switch (this) {
        case DAILY:
            return "[Daily]";
        case WEEKLY:
            return "[Weekly]";
        case MONTHLY:
            return "[Monthly]";
        case BIWEEKLY:
            return "[Biweekly]";
        default:
            return "[??]";
        }
    }
}
