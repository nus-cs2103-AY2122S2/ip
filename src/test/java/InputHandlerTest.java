import duke.ui.InputHandler;
import duke.ui.DukeException;

import java.io.IOException;
import java.io.File;

import java.util.Scanner;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputHandlerTest {

    @Test
    @DisplayName("test tasks")
    void AddTodoEventDeadline() throws IOException, DukeException {
        InputHandler ui = new InputHandler();
        ui.handleInput("todo test todo");
        ui.handleInput("event test event /at 2022-02-09 12:00");
        ui.handleInput("deadline test deadline /by 2022-02-11 03:59");
        String FILEPATH = "data/data.txt";
        File dataFile = new File(FILEPATH);
        Scanner sc = new Scanner(dataFile);
        assertEquals(sc.nextLine(), "[T] [X] / test todo");
        assertEquals(sc.nextLine(), "[E] [X] / test event / 2022-02-09 / 12:00");
        assertEquals(sc.nextLine(), "[D] [X] / test deadline / 2022-02-11 / 03:59");
    }
}

