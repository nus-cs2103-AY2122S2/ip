package duke.command;

import duke.dukeexceptions.InvalidCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestCommand{
    @Test
    void createCommand_noCommand_MissingCommandExceptionThrown() {
        try {
            Command cmd = Command.getCommand("", "");
            Assertions.fail();
        } catch (InvalidCommand e) {
            Assertions.assertEquals("Sorry, I don't see any commands!", e.getMessage());
        }
    }

    @Test
    void createCommand_randomCommand_IncorrectCommandExceptionThrown() {
        try {
            Command cmd = Command.getCommand("Some", "random command");
            Assertions.fail();
        } catch (InvalidCommand e) {
            Assertions.assertEquals("Sorry! I don't undestand what are you saying!", e.getMessage());
        }
    }

    @Test
    void createCommand_byeCommand_ByeCommand() {
        try {
            Command cmd = Command.getCommand("BYE", "");
            Assertions.assertInstanceOf(ByeCommand.class, cmd);
        } catch (InvalidCommand e) {
            Assertions.fail();
        }
    }
}