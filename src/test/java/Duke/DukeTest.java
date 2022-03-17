package Duke;


import duke.task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }


    @Test
    public void defaultConstructor_noInput_falseStatusTaskBuilt() {
        Task task = new Task("my task");
        assertEquals(false, task.getStatus());
    }

    @Test
    public void normalConstructor_trueInput_trueStatusTaskBuilt() {
        Task task = new Task("my task", true);
        assertEquals(true, task.getStatus());
    }


}
