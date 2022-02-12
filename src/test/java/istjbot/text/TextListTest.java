package istjbot.text;

import static istjbot.command.CommandEnum.DEADLINE;
import static istjbot.command.CommandEnum.EVENT;
import static istjbot.command.CommandEnum.TODO;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TextListTest {
    private TextList textList = new TextList();

    @BeforeEach
    public void setup() {
        // Make use of Stubs?
        textList.addTask(TODO, "a todo task", "");
        textList.addTask(EVENT, "an event task", "2021-02-03");
        textList.addTask(DEADLINE, "a deadline task", "2021-02-05");
    }

    @Test
    public void taskSize_threeEvents_three() {
        assertEquals(3, textList.taskListSize());
    }

    @Test
    public void taskString_indexOne_firstEventToString() {
        assertEquals("[T][ ] a todo task", textList.taskString(1));
    }

    @Test
    public void tasksToTxtString_threeEvents_txtStrings() {
        assertEquals("todo / 0 / a todo task\n"
                + "event / 0 / an event task / 2021-02-03\n"
                + "deadline / 0 / a deadline task / 2021-02-05", textList.textsToTxtString());
    }

    @Test
    public void deletedTaskString_indexTwo_secondEventToString() {
        assertEquals("[E][ ] an event task (at: Feb 3 2021)", textList.deletedTaskString(2));
    }

    @Test
    public void searchByDateString_invalidDate_exceptionThrown() {
        Assertions.assertThrows(DateTimeParseException.class, () -> {
            textList.searchByDateString("invalid date");
        });
    }

    @DisplayName("See whether task can be found with existing dates in the database")
    @ParameterizedTest
    @ValueSource(strings = {"2021-02-03", "2021-02-05"})
    public void searchByDateString_validDate_someTasks(String dateString) {
        Assertions.assertFalse(textList.searchByDateString(dateString).isEmpty());
    }

    @DisplayName("See whether task can be found with existing keyword in the database")
    @ParameterizedTest
    @ValueSource(strings = {"todo", "event", "deadline"})
    public void searchByKeywordString_validKeyword_someTasks(String keyword) {
        Assertions.assertFalse(textList.searchByKeywordString(keyword).isEmpty());
    }
}
