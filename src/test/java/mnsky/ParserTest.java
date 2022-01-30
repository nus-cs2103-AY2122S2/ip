package mnsky;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void testParseInputSuccess1() {
        String[] inputs = {"bye", "list", "mark 2", "mark 400 idk", "unmark 300", "delete 12489", "vioivvv"};
        ArrayList<ArrayList<String>> expected = new ArrayList<>();
        expected.add(new ArrayList<>(List.of("bye")));
        expected.add(new ArrayList<>(List.of("list")));
        expected.add(new ArrayList<>(List.of("mark", "2")));
        expected.add(new ArrayList<>(List.of("mark", "400")));
        expected.add(new ArrayList<>(List.of("unmark", "300")));
        expected.add(new ArrayList<>(List.of("delete", "12489")));
        expected.add(new ArrayList<>(List.of("invalid")));

        for (int i = 0; i < inputs.length; i++) {
            assertEquals(expected.get(i), Parser.parseInput(inputs[i]));
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

        ArrayList<ArrayList<String>> expected = new ArrayList<>();
        expected.add(new ArrayList<>(List.of("task", "idk")));
        expected.add(new ArrayList<>(List.of("task", "a b c 1 2 3")));
        expected.add(new ArrayList<>(List.of("deadline", "some thing", "att 123")));
        expected.add(new ArrayList<>(List.of("event", "what are you talking about   ???", "12:34 pm december 15")));

        for (int i = 0; i < inputs.length; i++) {
            assertEquals(expected.get(i), Parser.parseInput(inputs[i]));
        }
    }
}
