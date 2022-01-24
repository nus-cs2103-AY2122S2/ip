package mnsky;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testParseInputSuccess1() {
        String[] inputs = {"bye", "list", "mark 2", "mark 400 idk", "unmark 300", "delete 12489", "vioivvv"};
        ArrayList<String>[] expected = new ArrayList[]{
                new ArrayList<>(List.of("bye")),
                new ArrayList<>(List.of("list")),
                new ArrayList<>(List.of("mark", "2")),
                new ArrayList<>(List.of("mark", "400")),
                new ArrayList<>(List.of("unmark", "300")),
                new ArrayList<>(List.of("delete", "12489")),
                new ArrayList<>(List.of("invalid"))
        };

        for (int i = 0; i < inputs.length; i++) {
            assertEquals(expected[i], Parser.parseInput(inputs[i]));
        }
    }

    @Test
    public void testParseInputSuccess2() {
        String[] inputs = {
                "todo idk",
                "todo a b c 1 2 3",
                "deadline some thing /by att 123",
                "event what are you talking about   ??? /at 12:34 pm december 15"
        };

        ArrayList<String>[] expected = new ArrayList[]{
                new ArrayList<>(List.of("task", "idk")),
                new ArrayList<>(List.of("task", "a b c 1 2 3")),
                new ArrayList<>(List.of("deadline", "some thing", "att 123")),
                new ArrayList<>(List.of("event", "what are you talking about   ???", "12:34 pm december 15"))
        };

        for (int i = 0; i < inputs.length; i++) {
            assertEquals(expected[i], Parser.parseInput(inputs[i]));
        }
    }
}
