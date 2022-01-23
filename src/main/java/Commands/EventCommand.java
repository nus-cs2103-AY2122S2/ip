package Commands;

import Exceptions.EmptyDescriptionException;
import Exceptions.DukeException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class EventCommand {
    public static DukeBot.Event preProcessing(String input, String[] parts) throws DukeException {
        if (parts.length == 1) {
            throw new EmptyDescriptionException("event");
        }
        String description = input.substring(6);
        String eventParts[] = description.split("/");
        String[] dateTime = eventParts[1].substring(3).split(" ");
        String date = dateTime[0];

        if (dateTime.length < 2) {
            throw new DukeException("Please enter a time");
        }

        int time = Integer.parseInt(dateTime[1]);

        if (dateTime[1].length() < 4 || time < 0000 || time >= 2400) {
            throw new DukeException("Please enter a valid time");
        }

        int hour = Integer.parseInt(dateTime[1].substring(0,2));;
        int minute = Integer.parseInt(dateTime[1].substring(2,4));

        LocalTime localTime;

        LocalDate localDate = LocalDate.parse("2010-01-01");
        try {
            localDate = LocalDate.parse(date);
            localTime = LocalTime.of(hour, minute);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter a valid date in the format yyyy-mm-dd");
        } catch (DateTimeException e) {
            throw new DukeException("Please enter a valid time");
        }

        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);

        DukeBot.Event eventTask = new DukeBot.Event(eventParts[0], localDateTime);
        return eventTask;
    }
}
