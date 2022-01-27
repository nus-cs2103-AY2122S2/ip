package duke.parser;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeParserTest {

    @Test
    public void parseCommand_dateObject() {
        LocalDate date = DateTimeParser.parseDate("2020-12-12");
        String dateString = DateTimeParser.formatDate(date);
        assertEquals("Saturday, December 12, 2020", dateString);
    }

}
