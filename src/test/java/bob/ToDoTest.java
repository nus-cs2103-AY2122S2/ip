package bob;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ToDoTest {
    private static final String VALID_DESCRIPTION = "This is a valid ToDo instance.";

    @Test
    public void constructor_emptyDescription_throwsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new ToDo(""));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new ToDo("", true));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new ToDo("", false));
    }

    @Test
    public void isValidToDo() {
        final ToDo validTrueToDo = new ToDo(VALID_DESCRIPTION, true);
        final ToDo validFalseToDo = new ToDo(VALID_DESCRIPTION, false);

        final String validTrueToDoSavedEntry = "T|1|" + VALID_DESCRIPTION;
        final String validFalseToDoSavedEntry = "T|0|" + VALID_DESCRIPTION;

        Assertions.assertEquals(validTrueToDo.generateSavedEntry(), validTrueToDoSavedEntry);
        Assertions.assertEquals(validFalseToDo.generateSavedEntry(), validFalseToDoSavedEntry);

        final String validTrueToDoToString = "[T][X] " + VALID_DESCRIPTION;
        final String validFalseToDoToString = "[T][ ] " + VALID_DESCRIPTION;

        Assertions.assertEquals(validTrueToDo.toString(), validTrueToDoToString);
        Assertions.assertEquals(validFalseToDo.toString(), validFalseToDoToString);
    }
}
