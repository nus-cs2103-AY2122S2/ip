package com.duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testStringConversion() {
        assertEquals("[D][ ] return book (by: Oct 10 2019)",
                new Deadline("return book", "2019-10-10").toString());
    }

    @Test
    public void testGetDate() {
        assertEquals(LocalDate.parse("2020-10-10"),
                new Deadline("finish assignment", "2020-10-10").getDate());
    }

}
