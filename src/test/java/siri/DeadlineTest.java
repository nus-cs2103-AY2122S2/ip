package siri;

import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertEquals;

public class DeadlineTest {
    @Test
    public void stringFormatTest(){
        assertEquals("[D][ ] CS2103T Quiz 3 (by: May 20th)", new Deadline("CS2103T Quiz 3","D", "May 20th").toString());
    }
}
