package doge.stub;

import java.time.LocalDateTime;

import doge.task.Deadline;

public class DeadlineStub extends Deadline {

    public DeadlineStub() {
        super("test deadline", LocalDateTime.of(2022, 2, 22, 19, 10));
    }

}
