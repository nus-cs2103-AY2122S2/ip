package yeowoo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import tasks.TaskList;

public class ParserTest {
    @Test
    public void loadTest() {
        Throwable exception = assertThrows(InvalidTaskException.class, () ->
                new Parser(new TaskList()).parse("mark paper"));
        assertEquals("Invalid task number!", exception.getMessage());
    }

}
