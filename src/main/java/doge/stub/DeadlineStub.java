package doge.stub;

import doge.task.Deadline;

import java.time.LocalDateTime;

public class DeadlineStub extends Deadline {

    public DeadlineStub() {
       super("test deadline", LocalDateTime.of(2022, 2, 22, 19, 10));
    }

}
