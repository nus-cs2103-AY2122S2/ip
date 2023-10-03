package lily;

import lily.task.Task;
import lily.task.Todo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;


import org.junit.jupiter.api.DisplayName;
// https://github.com/nus-cs2103-AY2122S2/ip/pull/123/files#diff-981e05698b57c32bd8f57cfabc13f01b63151e890767033651aed32f54111120

@DisplayName("Testing TaskList Class")
public class TaskListTest {
    
    @DisplayName("Add Todo")
    @Test
    void addTodo_filledTaskList_success() {
        LinkedList<Task> ll = new LinkedList<>(Arrays.asList(new Todo("Test Add Todo")));
        TaskList tl = new TaskList(ll);
        String s = "Added Item";
        assertSame(tl.addTodo(s).getDesc(), s);
    }

    @DisplayName("Can Remove")
    @Test
    void remove_filledTaskList_success() {
        Task t = new Todo("Test Remove"); 
        TaskList tl = new TaskList(new LinkedList<>(Arrays.asList(t)));
        assertSame(tl.remove(0), t);
    }

    @DisplayName("Cannot Remove")
    @Test
    void remove_emptyTaskList_fail() {
        try {
            Task t = new Todo("Test Remove"); 
            TaskList tl = new TaskList(new LinkedList<>());
            assertEquals(tl.remove(5), t);
            fail("Should have thrown out of bounds error");
        } catch (IndexOutOfBoundsException ie) {
            assertSame(ie.getMessage(), LilyException.ERROR_OUT_OF_BOUNDS);
        }
    }

    @DisplayName("Test Example")
    @Test
    void whatIsBeingTested_descriptionOfTestInputs_expectedOutcome() throws Exception {
        assertEquals(1, 1);
    }
}