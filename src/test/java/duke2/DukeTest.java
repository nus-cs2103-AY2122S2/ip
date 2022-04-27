package duke2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DukeTest {
    @Test
    public void eventTestStringConversion() {
        assertEquals("[E][] event (at: 2020-12-12 1745)", new Event("event", "2020-12-12 1745").toString());
    }

    @Test
    public void todoTestStringConversion() {
        assertEquals("[T][] borrow book", new Todo("borrow book").toString());
    }

}