package yeowoo;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import tasks.TaskList;

public class ParserTest {
    @Test
    public void loadTest() throws MissingDescriptionException, InvalidTaskException {
        try {
            Assertions.assertEquals("Invalid command!", new Parser(new TaskList()).parse("mark paper"));
        } catch (YeowooException e) {
            assertEquals("Invalid command!", e.getMessage());
        }
    }
}
