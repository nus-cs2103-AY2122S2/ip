package yeowoo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tasks.TaskList;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
