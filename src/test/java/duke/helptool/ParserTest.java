package duke.helptool;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    @DisplayName("Determine valid date should work")
    void testValidDate() {
        assertTrue(Parser.isValidDate("12/10/2022 1800"), "Normal date should work");
        assertFalse(Parser.isValidDate("12/13/2022 1800"), "Invalid date should not work");
        assertFalse(Parser.isValidDate("2022 1800"), "Invalid date format should not work");
    }

    @Test
    @DisplayName("Determine empty input should work")
    void testValidEmpty() {
        assertTrue(Parser.isEmpty(""), "Empty string should work");
        assertTrue(Parser.isEmpty("  "), "String with space should work");
        assertFalse(Parser.isEmpty("a"), "Non Empty string should not work");
    }

}
