import duke.exception.ChiException;
import duke.parser.Parser;
import duke.storage.StorageStub;
import duke.tasklist.TaskListStub;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {

    @Test
    void textPrinted_listStatement_ListOfTasks() throws ChiException, IOException{
        Parser p = new Parser();
        TaskListStub tls = new TaskListStub();
        StorageStub ss = new StorageStub("/somePath.txt");
        try{
            assertEquals("list item 1\nlist item 2", p.processMessage("list", tls, ss));
        } catch (ChiException e) {
            throw new ChiException("Not supposed to happen");
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    @Test
    void outputMsg_incorrectCommand_ChiExceptionThrown() throws ChiException, IOException{
        Parser p = new Parser();
        TaskListStub tls = new TaskListStub();
        StorageStub ss = new StorageStub("/somePath.txt");
        try{
            String s = p.processMessage("allahu", tls, ss);
        } catch (ChiException e) {
            assertEquals("The following command allahu cannot be understood", e.toString());
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }
}