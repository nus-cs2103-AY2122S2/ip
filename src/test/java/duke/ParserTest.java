package duke;

import duke.tasks.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void checkCorrectnessOfParsing() {
        String commandFromFile = "E ; 0 ; sleep ; 06/02/2022 0000 - 0530";
        String[] commandFromFileDetails = commandFromFile.split(";", 4);
        Task parsedTaskFile = Parser.parseCommandFromFile(commandFromFileDetails);
        String commandFromCli = "event sleep /at 06/02/2022 0000 - 0530";
        Task parsedTaskCli = null;
        try {
            parsedTaskCli = Parser.parseCommandFromUser("event", commandFromCli);
        } catch (CustomException e) {
            e.printStackTrace();
        }
        assertEquals(parsedTaskFile.toString(), parsedTaskCli.toString());
    }
}
