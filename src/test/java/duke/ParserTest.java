package duke;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class ParserTest {
    @Test
    public void loadTest() throws MissingDescriptionException {
        try {
            assertEquals("Invalid command!", new Parser().parse("mark paper"));
        } catch (DukeException e) {
            assertEquals("Invalid command!", e.getMessage());
        }
    }
}
