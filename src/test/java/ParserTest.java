import karen.KarenException;
import karen.Parser;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void validDateFormat_correct_success() {
        try {
            assertEquals(LocalDate.of(2021, 1, 1).toString(),
                    new Parser().validateDateFormat("2021-01-01"));
            assertEquals(LocalDate.of(2021, 12, 31).toString(),
                    new Parser().validateDateFormat("2021-12-31"));
        } catch (KarenException err) {
            // nothing happens
        }
    }

    @Test
    public void validDateFormat_incorrect_exceptionThrown() {
        try {
            assertEquals(LocalDate.of(2021, 1, 1),
                    new Parser().validateDateFormat("2021-31-12"));
            fail();
        } catch (KarenException err) {
            assertEquals("Wrong date formatting. It should be in yyyy-mm-dd", err.toString());
        }
    }
}
