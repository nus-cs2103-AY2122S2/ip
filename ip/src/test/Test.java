//import duke.duke.Task;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class Test {
//    @Test
//    public void testGetDescription() {
//        String text = "party";
//        duke.Task testTask = new duke.Task(text);
//        assertEquals(text, testTask.getDescription());
//    }
//
//    @Test
//    public void testGetStatusIcon() {
//        duke.Task testTask = new duke.Task("stuff", true);
//        assertEquals("X", testTask.getStatusIcon());
//
//        testTask = new duke.Task("stuff", false);
//        assertEquals(" ", testTask.getStatusIcon());
//    }
//
//    @Test
//    public void testToString() {
//        duke.Task testTask = new duke.Task("playball", true);
//        assertEquals("[X] playball", testTask.toString());
//    }
//}