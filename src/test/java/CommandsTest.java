import chibot.commands.MarkCommand;
import chibot.exception.ChiException;
import chibot.storage.StorageStub;
import chibot.tasklist.TaskListStub;
import org.junit.jupiter.api.Test;

public class CommandsTest {

    @Test
    void outputString_userMarksATaskAsDone_TaskSuccessfullyMarked() throws ChiException {
        String[] tokens = {"1"};
        TaskListStub tls = new TaskListStub();
        StorageStub sges = new StorageStub("/dummy.txt");
        MarkCommand toTest = new MarkCommand(tokens);

    }
}
