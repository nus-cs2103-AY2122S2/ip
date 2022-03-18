package juke;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TagTest {
    @Test
    void correctTag() {
        ArrayList<Task> taskList = new ArrayList<>(10);
        taskList.add(new Todo("grocery", ""));
        taskList.add(new Event("meeting (at 12 December 2022, 12:55pm", ""));
        taskList.add(new Todo("assignment (by 13 December 2031, 12:44pm", ""));

        ArrayList<Task> expectedTaskList = new ArrayList<>(10);
        expectedTaskList.add(new Todo("grocery", ""));
        expectedTaskList.add(new Event("meeting (at 12 December 2022, 12:55pm", ""));
        expectedTaskList.add(new Todo("assignment (by 13 December 2031, 12:44pm", "#important"));

        String inputString = "tag 3 important";
        Parser.getResponse(inputString, taskList);
        String outputList = Generalcommands.executeList(taskList, new Outputs());
        String expectedOutput = Generalcommands.executeList(expectedTaskList, new Outputs());
        assert(outputList.equals(expectedOutput));
    }

    @Test
    void incorrectTag() {
        ArrayList<Task> taskList = new ArrayList<>(10);
        taskList.add(new Todo("grocery", ""));
        taskList.add(new Event("meeting (at 12 December 2022, 12:55pm", ""));
        taskList.add(new Todo("assignment (by 13 December 2031, 12:44pm", ""));

        ArrayList<Task> expectedTaskList = new ArrayList<>(10);
        expectedTaskList.add(new Todo("grocery", ""));
        expectedTaskList.add(new Event("meeting (at 12 December 2022, 12:55pm", ""));
        expectedTaskList.add(new Todo("assignment (by 13 December 2031, 12:44pm", "#important"));

        String inputString = "tag 3 softdeadline";
        Parser.getResponse(inputString, taskList);
        String outputList = Generalcommands.executeList(taskList, new Outputs());
        String expectedOutput = Generalcommands.executeList(expectedTaskList, new Outputs());
        assert(!outputList.equals(expectedOutput));
    }
}
