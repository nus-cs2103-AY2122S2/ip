package Helper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateHandlerTest {

    /**
     * tests if the isOnDate methods in DateHandler works appropriately.
     */
    @Test
    public void isOnDate_date_returnTrueOrFalse() {
        DateHandler case1 = new DateHandler("2022-05-19");
        assertEquals(true, case1.isOnDate("2022-05-19"));
        assertEquals(false, case1.isOnDate("2022-06-19"));
        assertEquals(false, case1.isOnDate("2022-04-10"));
        assertEquals(false, case1.isOnDate("2022-05-18"));
    }

    /**
     * tests if the isBefore methods in DateHandler works appropriately.
     */
    @Test
    public void isBefore_date_returnTrueOrFalse() {
        DateHandler case1 = new DateHandler("2022-05-19");
        assertEquals(false, case1.isBefore("2022-05-19"));
        assertEquals(true, case1.isBefore("2022-06-19"));
        assertEquals(false, case1.isBefore("2022-04-10"));
        assertEquals(false, case1.isBefore("2022-05-18"));

    }

}

