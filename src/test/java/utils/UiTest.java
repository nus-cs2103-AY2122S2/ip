package utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import echo.ui.Ui;


public class UiTest {
    private final Ui ui = new Ui();

    @Test
    @DisplayName("Empty list message should be shown")
    public void getEmptyListMessage_noInput_emptyListMessage() {
        String message = "Task list is empty!";
        assertEquals(message, ui.getEmptyListMessage());
    }

    @Test
    @DisplayName("Add task message should be shown")
    public void getAddMessage_validTaskStatus_expectedAddMessage() {
        String taskStatus = "1. [T][ ] read book";
        String message = "Got it. I've added this task: \n"
                + "  " + taskStatus + "\n"
                + "Now you have 1 tasks in the list.";
        assertEquals(message, ui.getAddMessage(taskStatus, 1));
    }

    @Test
    @DisplayName("Mark message should be shown")
    public void getMarkMessage_validTaskStatus_expectedMarkMessage() {
        String taskStatus = "1. [T][X] read book";
        String message = "Nice! The task is marked as done: \n" + "  " + taskStatus;
        assertEquals(message, ui.getMarkMessage(taskStatus));
    }

    @Test
    @DisplayName("Unmark message should be shown")
    public void getUnmarkMessage_validTaskStatus_expectedUnmarkMessage() {
        String taskStatus = "1. [T][ ] read book";
        String message = "OK! The task is unmarked: \n" + "  " + taskStatus;
        assertEquals(message, ui.getUnmarkMessage(taskStatus));
    }

    @Test
    @DisplayName("Delete message should be shown")
    public void getDeleteMessage_validTaskStatus_expectedDeleteMessage() {
        String taskStatus = "1. [T][ ] read book";
        String message = "Noted. I've removed the task: \n"
                + "  " + taskStatus + "\n"
                + String.format("Now you have %d tasks in the list.", 1);
        assertEquals(message, ui.getDeleteMessage(taskStatus, 1));
    }

    @Test
    @DisplayName("List message should be shown")
    public void getListMessage_validListStatus_expectedListMessage() {
        String message = "1. [T][ ] read book";
        assertEquals(message, ui.getListMessage(message));
    }

    @Test
    @DisplayName("Find message should be shown")
    public void getFindMessage_validTaskStatus_expectedFindMessage() {
        String taskStatus = "1. [T][ ] read book";
        String message = "Here are the matching tasks in your list:\n" + taskStatus;
        assertEquals(message, ui.getFindMessage(taskStatus));
    }

    @Test
    @DisplayName("Cant find message should be shown")
    public void getCantFindMessage_noInput_expectedCantFindMessage() {
        String message = "No task with that description";
        assertEquals(message, ui.getCantFindMessage());
    }

    @Test
    @DisplayName("Bye message should be shown")
    public void getByeMessage_noInput_expectedByeMessage() {
        String message = "Goodbye!";
        assertEquals(message, ui.getByeMessage());
    }
}
