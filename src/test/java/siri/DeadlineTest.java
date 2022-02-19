package siri;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void stringFormatTest(){
        assertEquals("[D][ ] CS2103T Quiz 3 (by: May 20th)", new Deadline("CS2103T Quiz 3","D", "May 20th").toString());
    }
}
