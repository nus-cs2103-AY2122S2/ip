package duke.utils;

public class Priority implements Comparable<Priority> {
    private static final int HIGH_LEVEL = 1;
    private static final int MEDIUM_LEVEL = 2;
    private static final int LOW_LEVEL = 3;
    private static final int INVALID_LEVEL = -1;
    private final int priorityLevel;

    /**
     * Creates a new Priority instance based on the given priority
     *
     * @param priorityLevel A PriorityLevel enum representing the given priority.
     */
    public Priority(PriorityLevel priorityLevel) {
        if (priorityLevel == PriorityLevel.HIGH) {
            this.priorityLevel = HIGH_LEVEL;
        } else if (priorityLevel == PriorityLevel.MEDIUM) {
            this.priorityLevel = MEDIUM_LEVEL;
        } else if (priorityLevel == PriorityLevel.LOW) {
            this.priorityLevel = LOW_LEVEL;
        } else {
            this.priorityLevel = INVALID_LEVEL;
        }
    }

    /**
     * Creates a new Priority instance based on the given priority
     *
     * @param priorityLevel A String representing high, medium or low priority.
     */
    public Priority(String priorityLevel) {
        if (priorityLevel.equalsIgnoreCase("HIGH")) {
            this.priorityLevel = HIGH_LEVEL;
        } else if (priorityLevel.equalsIgnoreCase("MEDIUM")) {
            this.priorityLevel = MEDIUM_LEVEL;
        } else if (priorityLevel.equalsIgnoreCase("LOW")) {
            this.priorityLevel = LOW_LEVEL;
        } else {
            this.priorityLevel = INVALID_LEVEL;
        }
    }

    @Override
    public int compareTo(Priority o) {
        if (priorityLevel == INVALID_LEVEL && o.priorityLevel != INVALID_LEVEL) {
            return 1;
        }
        return priorityLevel - o.priorityLevel;
    }

    @Override
    public String toString() {
        if (priorityLevel == HIGH_LEVEL) {
            return "HIGH";
        } else if (priorityLevel == MEDIUM_LEVEL) {
            return "MEDIUM";
        } else if (priorityLevel == LOW_LEVEL) {
            return "LOW";
        } else {
            return "INVALID";
        }
    }
}
