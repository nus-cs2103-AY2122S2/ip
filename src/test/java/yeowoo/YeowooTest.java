package yeowoo;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class YeowooTest {
    @Test
    public void initialisationTest() {
        try {
            Yeowoo d = new Yeowoo("Test/test.txt");
        } catch (Exception e) {
            fail("No exception expected");
        }
    }

}
