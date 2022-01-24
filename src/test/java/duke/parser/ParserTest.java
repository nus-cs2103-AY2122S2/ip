package duke.parser;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    private Parser parser;
    private TaskList tasks;
    private Ui ui;
    private Storage storage;


    @BeforeEach
    public void setUp() {
        parser = new Parser();
        tasks = new TaskList(new ArrayList<>());
        storage = new Storage("/ip/src/test/data");
    }

    @Test
    public void parse_emptyInput_returnsIncorrectCommand() {
        String[] emptyInputs = {"", " ", "\n \n"};
        String errorMessage = "My apologies, but it seems that I do not understand your request.";
        for (String s : emptyInputs) {
            assertEquals(parser.parseCommand(s).execute(tasks, ui, storage), errorMessage);
        }
    }

    @Test
    public void parse_byeInput_returnsExitCommand() {
        String[] byeInputs = {"bye", "  bye", "bye   ", "  bye  "};
        String byeMessage = "Goodbye! Till the next time we meet!";
        for (String s : byeInputs) {
            assertEquals(parser.parseCommand(s).execute(tasks, ui, storage), byeMessage);
        }
    }
}
