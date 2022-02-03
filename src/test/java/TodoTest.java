
import myPackage.Deadlines;
import myPackage.ToDos;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void todoTest(){
        assertEquals(new ToDos("HELLO").getTaskType(),"T");
    }

    @Test
    public void deadlineTest(){
        assertEquals(new Deadlines("HI","BYE").getTaskType(),"D");
    }
}
