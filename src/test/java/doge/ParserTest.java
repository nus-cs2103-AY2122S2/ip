package doge;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import doge.command.Command;
import doge.exception.DogeException;
import doge.stub.DeadlineCommandStub;
import doge.stub.TaskListStub;
import doge.stub.UiStub;
import doge.stub.StorageStub;


public class ParserTest {
    @Test
    public void testParse() {
        try {
            DeadlineCommandStub deadlineCommandStub = new DeadlineCommandStub();
            Command deadlineCommandActual = Parser.parse("deadline test deadline / 2022-02-22 19:10");
            deadlineCommandActual.execute(new TaskListStub(), new UiStub(), new StorageStub());
            assertEquals(deadlineCommandStub.getTask().toString(), deadlineCommandActual.getTask().toString());
        } catch (DogeException e) {
            fail();
        }
    }
}
