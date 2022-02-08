package saitama.tasks;

import java.util.Arrays;
import java.util.HashMap;

/**
 * A list of tags for recurring tasks.
 */
public enum RecursiveTag {
    WEEKLY("--rw"), BIWEEKLY("--rb"), DAILY("--rd"), MONTHLY("--rm");

    private static HashMap<String, RecursiveTag> tagMap = new HashMap<>();
    private String tag;

    static {
        Arrays.stream(RecursiveTag.values()).forEach(option -> tagMap.put(option.tag, option));
    }
    RecursiveTag(String tag) {
        this.tag = tag;
    }

    /**
     * Gets the RecursiveTag corresponding to the given tag, if it exists. Otherwise, returns null.
     *
     * @param tag
     * @return
     */
    public static RecursiveTag get(String tag) {
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
