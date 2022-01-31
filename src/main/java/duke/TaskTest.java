package duke;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.instanceOf;

import static org.hamcrest.MatcherAssert.assertThat;

class TaskTest {
    @Test
    public void testInstace() {
        ToDo todo = new ToDo("test");
        assertThat(todo, instanceOf (Task.class));
    }
}