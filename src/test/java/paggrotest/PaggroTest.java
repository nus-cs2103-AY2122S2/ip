package paggrotest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import paggro.command.Command;
import paggro.lister.Lister;
import paggro.parser.Parser;
import paggro.storage.Storage;
import paggro.task.ToDo;
import paggro.ui.Ui;

public class PaggroTest {
    public class CommandStub extends Command {
        public CommandStub() {
            super();
        }

        @Override
        public String execute(Lister lister, Ui ui, Storage storage) {
            return "";
        }
    }
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
        assertEquals("T | true | Nothing | ", new ToDo("Nothing", true).parseTask());
    }

    @Test
    void toDoParseTask_toDoNothingFalse_parsedToDoString() {
        assertEquals("T | false | Nothing | ", new ToDo("Nothing").parseTask());
    }
}
