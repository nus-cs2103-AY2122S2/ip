package stevie.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import stevie.task.types.ToDoTask;

class ToDoTaskTest {

    @Test
    void generateTaskSaveData() {
        ToDoTask task = new ToDoTask("cs2103 lecture");
        assertEquals("T|0|cs2103 lecture", task.generateTaskSaveData());
    }
}
