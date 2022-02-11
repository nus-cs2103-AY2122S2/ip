package duke.interfaces;

import java.time.LocalDateTime;

/**
 * Defines any objects that have some sort of time attributed to it.
 */
public interface Timable {
    /**
     * Gets the date time definition of the object.
     * @return the date time definition of the object.
     */
    public LocalDateTime getDateTime();
}
