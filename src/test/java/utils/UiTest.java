package utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import echo.ui.Ui;


public class UiTest {
    private final Ui ui = new Ui();

    @Test
    @DisplayName("Empty list message should be shown")
    public void testShowEmptyList() {
        String message = "Task list is empty!";
        assertEquals(message, ui.showEmptyList());
    }

    @Test
    @DisplayName("Add task message should be shown")
    public void testShowAdd() {
        String taskStatus = "1. [T][ ] read book";
        String message = "Got it. I've added this task: \n"
                + "  " + taskStatus + "\n"
                + "Now you have 1 tasks in the list.";
        assertEquals(message, ui.showAdd(taskStatus, 1));
    }

    @Test
    @DisplayName("Mark message should be shown")
    public void testShowMark() {
        String taskStatus = "1. [T][X] read book";
        String message = "Nice! The task is marked as done: \n" + "  " + taskStatus;
        assertEquals(message, ui.showMark(taskStatus));
    }

    @Test
    @DisplayName("Unmark message should be shown")
    public void testShowUnmark() {
        String taskStatus = "1. [T][ ] read book";
        String message = "OK! The task is unmarked: \n" + "  " + taskStatus;
        assertEquals(message, ui.showUnmark(taskStatus));
    }

    @Test
    @DisplayName("Delete message should be shown")
    public void testShowDelete() {
        String taskStatus = "1. [T][ ] read book";
        String message = "Noted. I've removed the task: \n"
                + "  " + taskStatus + "\n"
                + String.format("Now you have %d tasks in the list.", 1);
        assertEquals(message, ui.showDelete(taskStatus, 1));
    }

    @Test
    @DisplayName("List message should be shown")
    public void testShowList() {
        String message = "1. [T][ ] read book";
        assertEquals(message, ui.showList(message));
    }

    @Test
    @DisplayName("Find message should be shown")
    public void testShowFind() {
        String taskStatus = "1. [T][ ] read book";
        String message = "Here are the matching tasks in your list:\n" + taskStatus;
        assertEquals(message, ui.showFind(taskStatus));
    }

    @Test
    @DisplayName("Cant find message should be shown")
    public void testShowCantFind() {
        String message = "No task with that description";
        assertEquals(message, ui.showCantFind());
    }

    @Test
    @DisplayName("Bye message should be shown")
    public void showByeMessage() {
        String message = "Goodbye!";
        assertEquals(message, ui.sayBye());
    }
}
