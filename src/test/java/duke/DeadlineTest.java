package duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    void deadlineToString() {
        Deadline deadline = new Deadline("New deadline", "2/12/2022 2000");
        Assertions.assertEquals("[D][ ] New deadline (by: 02/12/2022 2000)", deadline.toString());
        System.out.println("### deadline toString() successful! ###");
    }

    @Test
    void deadlineMarkComplete() {
        Deadline deadline = new Deadline("New deadline", "2/12/2022 2000");
        deadline.markAsComplete();
        Assertions.assertEquals("[D][X] New deadline (by: 02/12/2022 2000)", deadline.toString());
        System.out.println("### deadline markAsComplete() successful! ###");
    }
}
