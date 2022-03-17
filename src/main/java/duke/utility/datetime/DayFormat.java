package duke.utility.datetime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DayFormat implements TimeStandard {

    public final List<String> DAYS = Arrays.asList(
            "^MON(DAY)?$", "^TUE(SDAY)?$", "^WED(NESDAY)?$",
            "^THU(RSDAY)?$", "^FRI(DAY)?$", "^SAT(URDAY)?$", "^SUN(DAY)?$");

    public LocalDateTime mapToLocalDateTime(String date) {
        try {
            return DAYS.stream().filter(d -> date.toUpperCase().matches(d)).map(d ->
                    LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0))
                            .with(TemporalAdjusters.next(DayOfWeek.of(DAYS.indexOf(d) + 1)))
            ).collect(Collectors.toList()).get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
    @Override
    public boolean isTime(String date) {
        return DAYS.stream().anyMatch(day -> date.toUpperCase().matches(day));
    }

}
