package duke.ParserTest;

import duke.Parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void validTodo() {
        String[] check = {"todo","make cake"};
        String[] out = Parser.parseInput("todo make cake");
        assertEquals(2, out.length);
        assertEquals(check[0], out[0]);
        assertEquals(check[1], out[1]);
    }
}
