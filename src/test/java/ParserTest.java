import johnny.Parser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

public class ParserTest {

    @Test
    public void parse_addTodoTask_success() throws Exception{
        ArrayList<String> expected = new ArrayList<>();
        expected.add(Parser.ADD_TODO);
        expected.add("Attend tutorial");

        assertEquals(expected, new Parser("todo Attend tutorial").parse());
    }


}
