package duke.misc;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;

import duke.exception.InvalidDateTime;

/**
 * Deals with the checking validity of date and time.
 *
 * @author Terng Yan Long
 */
public class DateTimeChecker {
    /**
     * Check if date/time specified is in the present.
     *
     * @param newDate Date to be tested.
     * @param newStartTime Start time to be tested.
     * @param newEndTime End time to be tested.
     */
    public static void checkDateTime(LocalDate newDate, LocalTime newStartTime, LocalTime newEndTime) {
        Clock cl = Clock.systemUTC();
        LocalDate nowDate = LocalDate.now(cl);
        LocalTime nowTime = LocalTime.now(cl);
        if (newDate.isBefore(nowDate)) {
            throw new InvalidDateTime("You cannot travel back in time!");
        }
        if (newStartTime != null) {
            if (newDate.isEqual(nowDate) & newStartTime.isBefore(nowTime)) {
                throw new InvalidDateTime("You cannot travel back in time!");
            } else if (newEndTime != null) {
                if (newDate.isEqual(nowDate) & newEndTime.isBefore(nowTime)) {
                    throw new InvalidDateTime("You cannot travel back in time!");
                } else if (!newEndTime.isAfter(newStartTime)) {
                    throw new InvalidDateTime("The end time must be after the start time!");
                }
            }
        }
    }

    /**
     * Checks if date/time specified is in the present.
     *
     * @param newDate Date to be tested.
     * @param newTime Time to be tested.
     */
    public static void checkDateTime(LocalDate newDate, LocalTime newTime) {
        Clock cl = Clock.systemUTC();
        LocalDate nowDate = LocalDate.now(cl);
        LocalTime nowTime = LocalTime.now(cl);
        if (newDate.isBefore(nowDate)) {
            throw new InvalidDateTime("You cannot travel back in time!");
        }
        if (newTime != null) {
            if (newDate.isEqual(nowDate) & newTime.isBefore(nowTime)) {
                throw new InvalidDateTime("You cannot travel back in time!");
            }
        }
    }
}
