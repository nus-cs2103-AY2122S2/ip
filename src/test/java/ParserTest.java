import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import duke.helper.Parser;

public class ParserTest {
    @Test
    void keywordTest() {
        String[] strArr = new String[]{"happy", "new", "year"};
        assertEquals(false, Parser.hasKeyword(strArr, "sad"));
        assertEquals(true, Parser.hasKeyword(strArr, "happy"));
    }

    @Test
    void isDateTest() {
        assertEquals(true, Parser.isDate("2020-02-03"));
    }
}
