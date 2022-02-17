package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void todoTest_newTask_success(){
        assertEquals("[T][ ] Attend Lecture",
                new Todo("Attend Lecture").toString());
    }
}
