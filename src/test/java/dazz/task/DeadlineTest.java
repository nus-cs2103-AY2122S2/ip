package dazz.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import dazz.Ui;
import dazz.exception.DazzException;

public class DeadlineTest {

    @Test
    public void testGetDateTimeFormat_success1() {
        try {
            Deadline deadline1 = new Deadline("return book", Ui.toLocalDateTime("12/12/2021 1800"));
            assertEquals("12 Dec 2021, 06:00PM", deadline1.getDateTimeFormat());
        } catch (DazzException e) {
            System.out.println("This is not supposed to run");
        }
    }

    @Test
    public void testGetDateTimeFormat_success2() {
        try {
            Deadline deadline2 = new Deadline("submit homework", Ui.toLocalDateTime("30-11-2021 1821"));
            assertEquals("30 Nov 2021, 06:21PM", deadline2.getDateTimeFormat());
        } catch (DazzException e) {
            System.out.println("This is not supposed to run");
        }
    }

    @Test
    public void testWriteToFile_success1() {
        try {
            Deadline deadline1 = new Deadline("return book", Ui.toLocalDateTime("12/12/2021 1800"));
            assertEquals("D === 0 === return book === 12 Dec 2021, 06:00PM", deadline1.writeToFile());
        } catch (DazzException e) {
            System.out.println("This is not supposed to run");
        }
    }

    @Test
    public void testWriteToFile_success2() {
        try {
            Deadline deadline2 = new Deadline("submit homework", true, Ui.toLocalDateTime("30-11-2021 1821"));
            assertEquals("D === 1 === submit homework === 30 Nov 2021, 06:21PM", deadline2.writeToFile());
        } catch (DazzException e) {
            System.out.println("This is not supposed to run");
        }
    }

    @Test
    public void testToString_success() {
        try {
            Deadline deadline = new Deadline("submit homework", true, Ui.toLocalDateTime("30-11-2021 1821"));
            assertEquals("[D][X] submit homework (by: 30 Nov 2021, 06:21PM)", deadline.toString());
        } catch (DazzException e) {
            System.out.println("This is not supposed to run");
        }
    }
}
