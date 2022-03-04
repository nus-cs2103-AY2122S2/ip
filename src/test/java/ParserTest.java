import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import exception.JarvisException;
import parser.Parser;

public class ParserTest {
    @Test
    public void parser_addTodo_success() {
        try {
            String input = "todo cs2105 tutorial";
            HashMap<String, Object> parsedInput = Parser.parseInput(input);
            assertEquals("TODO", parsedInput.get("command"));
            assertEquals("cs2105 tutorial", parsedInput.get("description"));
        } catch (JarvisException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parser_addDeadline_success() {
        try {
            String input = "deadline return book /by 2022-01-31 1800";
            HashMap<String, Object> parsedInput = Parser.parseInput(input);
            assertEquals("DEADLINE", parsedInput.get("command").toString());
            assertEquals("return book", parsedInput.get("description").toString());
            assertEquals(LocalDateTime.of(2022, Month.JANUARY, 31, 18, 0), parsedInput.get("date"));
        } catch (JarvisException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parser_addEvent_success() {
        try {
            String input = "event group project meeting /at 2022-02-20 1430";
            HashMap<String, Object> parsedInput = Parser.parseInput(input);
            assertEquals("EVENT", parsedInput.get("command").toString());
            assertEquals("group project meeting", parsedInput.get("description").toString());
            assertEquals(LocalDateTime.of(2022, Month.FEBRUARY, 20, 14, 30), parsedInput.get("date"));
        } catch (JarvisException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parser_getList_success() {
        try {
            String input = "list";
            HashMap<String, Object> parsedInput = Parser.parseInput(input);
            assertEquals("LIST", parsedInput.get("command").toString());
        } catch (JarvisException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parser_mark_success() {
        try {
            String input = "mark 2";
            HashMap<String, Object> parsedInput = Parser.parseInput(input);
            assertEquals("MARK", parsedInput.get("command"));
            assertEquals(1, parsedInput.get("num"));
        } catch (JarvisException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parser_unmark_success() {
        try {
            String input = "unmark 2";
            HashMap<String, Object> parsedInput = Parser.parseInput(input);
            assertEquals("UNMARK", parsedInput.get("command"));
            assertEquals(1, parsedInput.get("num"));
        } catch (JarvisException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parser_delete_success() {
        try {
            String input = "delete 3";
            HashMap<String, Object> parsedInput = Parser.parseInput(input);
            assertEquals("DELETE", parsedInput.get("command"));
            assertEquals(2, parsedInput.get("num"));
        } catch (JarvisException e) {
            e.printStackTrace();
        }
    }
}
