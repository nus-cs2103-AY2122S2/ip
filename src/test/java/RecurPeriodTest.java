import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import dukeclasses.DukeException;
import dukeclasses.RecurPeriod;

public class RecurPeriodTest {
    @Test
    public void testCreateRecurPeriod() {
        try {
            RecurPeriod test = RecurPeriod.createRecurPeriod("1 week");
            assertEquals(7, test.getPeriod().getDays());
        } catch (DukeException error) {
            fail();
        }

    }
}
