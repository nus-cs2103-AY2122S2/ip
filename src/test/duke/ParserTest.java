package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void parse_byeInstruction() {
        TasksList tasksList = new TasksList();
        Storage storage = new Storage();
        Parser parser = new Parser();

        String log = parser.parse("bye", tasksList, storage);
        assertEquals("BYE", log);
    }

    @Test
    void parse_listZeroInstruction() {
        TasksList tasksList = new TasksList();
        Storage storage = new Storage();
        Parser parser = new Parser();

        String log = parser.parse("list", tasksList, storage);
        assertEquals("You have 0 task", log);
    }
}