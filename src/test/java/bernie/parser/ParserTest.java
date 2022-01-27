package bernie.parser;

import bernie.enums.Type;
import bernie.tasks.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseListTypeTest() {
        assertEquals(Type.LIST, new Parser(new TaskList()).parseType("list"));
    }

    @Test
    public void parseByeTypeTest() {
        assertEquals(Type.BYE, new Parser(new TaskList()).parseType("bye"));
    }

    @Test
    public void parseMarkTypeTest() {
        assertEquals(Type.MARK, new Parser(new TaskList()).parseType("mark"));
    }

    @Test
    public void parseEmptyTypeTest() {
        assertEquals(Type.EMPTY, new Parser(new TaskList()).parseType(""));
    }

    @Test
    public void parseDeleteTypeTest() {
        assertEquals(Type.DELETE, new Parser(new TaskList()).parseType("delete"));
    }

    @Test
    public void parseAddTypeTest() {
        assertEquals(Type.ADD, new Parser(new TaskList()).parseType("deadline"));
    }
}
