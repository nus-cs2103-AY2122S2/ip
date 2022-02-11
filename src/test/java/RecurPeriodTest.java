import dukeclasses.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dukeclasses.RecurPeriod;
import static org.junit.jupiter.api.Assertions.fail;

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
