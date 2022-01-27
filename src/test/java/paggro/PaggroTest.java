package paggro;

import org.junit.jupiter.api.Test;
import paggro.parser.Parser;
import paggro.task.ToDo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class PaggroTest {
    @Test
    void parse_unknownCommandString_throwPaggroException() {
        try {
            assertEquals(new CommandStub(), Parser.parse("unknownString"));
            fail();
        } catch (Exception e) {
            assertEquals("    Come on... You don't actually expect me to understand that right... =.=",
                    e.getLocalizedMessage());
        }
    }

    @Test
    void toDoParseTask_toDoNothingTrue_parsedToDoString() {
        assertEquals("T | true | Nothing", new ToDo("Nothing", true).parseTask());
    }

    @Test
    void toDoParseTask_toDoNothingFalse_parsedToDoString() {
        assertEquals("T | false | Nothing", new ToDo("Nothing").parseTask());
    }
}
