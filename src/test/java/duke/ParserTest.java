package duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    void parseTest() {
        String deadlineCommand = "deadline return book /by 4/12/2020 1800";
        Assertions.assertEquals(Parser.parse(deadlineCommand), Command.DEADLINE);

        String eventCommand = "event project meeting /at 3/12/2019 1900 to 2100";
        Assertions.assertEquals(Parser.parse(eventCommand), Command.EVENT);

        String eventCommand2 = "event 2.4km run /at 28/12/2019 1600 to 1800";
        Assertions.assertEquals(Parser.parse(eventCommand2), Command.EVENT);

        String listCommand = "list";
        Assertions.assertEquals(Parser.parse(listCommand), Command.LIST);

        String markCommand = "mark 1";
        Assertions.assertEquals(Parser.parse(markCommand), Command.MARK);

        String deleteCommand = "delete 2";
        Assertions.assertEquals(Parser.parse(deleteCommand), Command.DELETE);

        String unmarkCommand = "unmark 1";
        Assertions.assertEquals(Parser.parse(unmarkCommand), Command.UNMARK);

        String byeCommand = "bye";
        Assertions.assertEquals(Parser.parse(byeCommand), Command.BYE);

        String emptyCommand = " ";
        Assertions.assertEquals(Parser.parse(emptyCommand), Command.ERROR);

        System.out.println("### Parser parse() successful! ###");
    }
}
