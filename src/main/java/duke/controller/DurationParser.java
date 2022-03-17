package duke.controller;

import duke.exception.DukeException;
import duke.exception.DurationEnumFormat;
import duke.exception.DurationFormatException;
import duke.utils.Pair;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Class for parsing the duration for event class
 */
public class DurationParser implements Parser<Pair<LocalDateTime, LocalDateTime>> {
    public static final String DURATION_SYMBOL = "~";
    public static final String TIME_SYMBOL = ":";
    public String duration;

    public DurationParser(String duration) {
        this.duration = duration;
    }

    /**
     * Check whether the time format is correct
     * @param strTime
     * @return true if the time format is correct
     */
    private boolean checkTimeFormat(String strTime) {
        return strTime.matches("^(2[0-3]|[01]?[0-9]):([0-5]?[0-9])$");
    }

    /**
     * Get the start and end time pair for command
     * @return Pair of start and end time
     * @throws DukeException
     */
    @Override
    public Pair<LocalDateTime, LocalDateTime> parse() throws DukeException {

        String[] splitDuration = duration.split(DURATION_SYMBOL);
        try {
            LocalDateTime startTime = new DateParser(splitDuration[0].trim()).parse();
            String endTimeInput = splitDuration[1].trim();
            LocalDateTime endTime;
            if (checkTimeFormat(endTimeInput)) {
                String[] splitTime = endTimeInput.split(TIME_SYMBOL);
                int hour = Integer.parseInt(splitTime[0]);
                int minute = Integer.parseInt(splitTime[1]);
                endTime = startTime.with(LocalTime.of(hour, minute));
            } else {
                endTime = new DateParser(endTimeInput).parse();
            }

            if (startTime.isAfter(endTime)) {
                throw new DurationFormatException("Start time should be before the end time",
                        DurationEnumFormat.WRONG_INTERVAL_DURATION);
            }
            return new Pair<>(startTime, endTime);
        } catch (IndexOutOfBoundsException e) {
            throw new DurationFormatException("Duration is not complete", DurationEnumFormat.WRONG_FORMAT_DURATION);
        }
    }


}
