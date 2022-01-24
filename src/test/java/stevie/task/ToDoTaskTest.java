package stevie.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToDoTaskTest {

    @Test
    void generateTaskSaveData() {
        ToDoTask task = new ToDoTask("cs2103 lecture");
        assertEquals("T|0|cs2103 lecture", task.generateTaskSaveData());
    }
}