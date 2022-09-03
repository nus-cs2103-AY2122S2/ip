package com.duke.modules;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.duke.tasks.Task;
import com.duke.tasks.Todo;

public class DukeTasklistTest {
    @Test
    public void displayFilledListTest() {
        TaskList testModule = new TaskList();
        ArrayList<Task> testList = new ArrayList<>();
        Task testTask = new Todo("testing");
        testList.add(testTask);
        testModule.setListWithoutSaving(testList);
        assertEquals(testModule.displayList(), "1. [T][ ] testing");
    }

    @Test
    public void displayEmptyListTest() {
        TaskList testModule = new TaskList();
        ArrayList<Task> testList = new ArrayList<>();
        testModule.setListWithoutSaving(testList);
        assertEquals(testModule.displayList(), "Your list is empty!");
    }
}
