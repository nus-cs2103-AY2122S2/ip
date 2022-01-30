import duke.ui.Duke;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void DukeConstructor_wrongFileInput_exceptionThrown(){
        try {
            new Duke("C:/repos/ip/dat/tasks.txt");
        } catch (Exception e) {
            assertEquals("LOADING ERROR", e.getMessage());
        }
    }

    @Test
    public void DukeConstructor_rightFileInput_exceptionNotThrown() {
        try {
            new Duke("C:/repos/ip/data/tasks.txt");
        } catch (Exception e) {
            assertDoesNotThrow(() -> e);
        }
    }
}