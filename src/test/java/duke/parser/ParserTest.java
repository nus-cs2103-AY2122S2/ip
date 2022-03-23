package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ParserTest {
    private Parser parser;
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        try {
            parser = new Parser();
            tasks = new TaskList(new ArrayList<>());
            storage = new Storage("/ip/src/test/data");
            ui = new Ui();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parse_emptyInput_returnsErrorMessage() {
        String[] emptyInputs = {"", " ", "\n \n"};
        String errorMessage = "Please provide me with the command that you would like to execute!";
        for (String s : emptyInputs) {
            try {
                assertEquals(parser.parseCommand(s).execute(tasks, ui, storage), errorMessage);
            } catch (DukeException e) {
                assertEquals(e.getMessage(), errorMessage);
            }
        }
    }

    @Test
    public void parse_byeInput_returnsExitCommand() {
        String[] byeInputs = {"bye", "  bye", "bye   ", "  bye  "};
        String byeMessage = "Goodbye! Till the next time we meet!";
        try {
            for (String s : byeInputs) {
                assertEquals(parser.parseCommand(s).execute(tasks, ui, storage), byeMessage);
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parse_unknownInput_returnsErrorMessage() {
        String[] unknownInputs = {"hello", "remark", "update", "lol", "hi"};
        String errorMessage = "My apologies, but it seems that I do not understand your request.";
        for (String s : unknownInputs) {
            try {
                assertEquals(parser.parseCommand(s).execute(tasks, ui, storage), errorMessage);
            } catch (DukeException e) {
                assertEquals(e.getMessage(), errorMessage);
            }
        }
    }
}
