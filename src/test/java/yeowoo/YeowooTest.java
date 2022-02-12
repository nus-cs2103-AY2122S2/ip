package yeowoo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class YeowooTest {
    @Test
    public void initialisationTest(){
        try{
            Yeowoo d = new Yeowoo("Test/test.txt");
        } catch (Exception e) {
            fail("No exception expected");
        }
    }

}
