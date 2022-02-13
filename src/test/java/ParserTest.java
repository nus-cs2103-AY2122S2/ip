import chibot.commands.Keywords;
import chibot.exception.ChiException;
import chibot.parser.Parser;
import chibot.storage.StorageStub;
import chibot.tasklist.TaskListStub;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {

    @Test
    void textPrinted_listStatement_ListOfTasks() throws ChiException, IOException{
        Parser p = new Parser();
        TaskListStub tls = new TaskListStub();
        StorageStub ss = new StorageStub("/somePath.txt");
        try {
            assertEquals("1. list item 1\n2. list item 2", p.processMessage("list", tls, ss));
        } catch (ChiException e) {
            throw new ChiException("Not supposed to happen");
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    @Test
    void outputMessage_incorrectCommand_ChiExceptionThrown() throws IOException {
        Parser p = new Parser();
        TaskListStub tls = new TaskListStub();
        StorageStub ss = new StorageStub("/somePath.txt");
        try {
            String s = p.processMessage("allahu", tls, ss);
        } catch (ChiException e) {
            assertEquals("Command not found nyan!", e.toString());
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    @Test
    void outputMessage_properlyDefinedCommand_KeywordEnumReturned() throws ChiException {
        Parser p = new Parser();
        try {
            String[] tokensToTest = {"todo", "teeth"};
            assertEquals(Keywords.ADD, p.inspectMessage(tokensToTest));
        } catch (ChiException e) {
            throw new ChiException("Not supposed to happen");
        }
    }

    @Test
    void outputMessage_tooShortTodoCommand_ChiExceptionThrown() throws ChiException {
        Parser p = new Parser();
        try {
            String[] tokensToTest = {"todo"};
            Keywords k = p.inspectMessage(tokensToTest);
        } catch (ChiException e) {
            assertEquals("Hey this command is too short nyan!", e.toString());
        }
    }

    @Test
    void outputMessage_helpTooMuchDescription_ChiExceptionThrown() throws ChiException {
        Parser p = new Parser();
        try {
            String[] tokensToTest = {"help", "list", "bad", "format"};
            Keywords k = p.inspectMessage(tokensToTest);
        } catch (ChiException e) {
            assertEquals("Hey Chi-san can only help you with one thing at a time nyan!", e.toString());
        }
    }
}