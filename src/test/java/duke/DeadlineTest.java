package duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class DeadlineTest {
    @Test
    void deadlineToString() {
        LocalDateTime dateTime = Parser.parseDateTime("2/12/2022 2000");
        Deadline deadline = new Deadline("New deadline", dateTime);
        Assertions.assertEquals("[D][ ] New deadline (by: 02/12/2022 2000)", deadline.toString());
        System.out.println("### deadline toString() successful! ###");
    }

    @Test
    void deadlineMarkComplete() {
        LocalDateTime dateTime = Parser.parseDateTime("2/12/2022 2000");
        Deadline deadline = new Deadline("New deadline", dateTime);
        deadline.markAsComplete();
        Assertions.assertEquals("[D][X] New deadline (by: 02/12/2022 2000)", deadline.toString());
        System.out.println("### deadline markAsComplete() successful! ###");
    }
}
