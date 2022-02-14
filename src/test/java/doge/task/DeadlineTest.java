package doge.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import doge.stub.DeadlineStub;

public class DeadlineTest {
    @Test
    public void testDeadlineToString() {
        String expected = "D |   | test deadline | DEADLINE: 22-Feb-2022 19:10";
        assertEquals(expected, new DeadlineStub().toString());

    }

}
