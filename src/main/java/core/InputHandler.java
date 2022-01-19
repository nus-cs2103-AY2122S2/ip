package core;

import utilities.OutputFormatter;

import static java.lang.Integer.parseInt;

public class InputHandler {
    private TaskList taskList;

    private InputHandler() {
        this.taskList = TaskList.getInstance();
    }

    public static InputHandler getInstance() {
        return new InputHandler();
    }

    public String handleInput(String inputData) {
        InputType inputType = InputValidator.determineInputType(inputData);
        OutputFormatter outputFormatter = OutputFormatter.getInstance();

        switch (inputType) {
            case ADD :
                handleAdd(inputData, outputFormatter);
                break;
            case MARK :
                handleMark(inputData, outputFormatter);
                break;
            case UNMARK :
                handleUnmark(inputData, outputFormatter);
                break;
            case LIST :
                handleList(outputFormatter);
                break;
            case TODO :
                handleToDo(inputData, outputFormatter);
                break;
            case DEADLINE :
                handleDeadline(inputData, outputFormatter);
                break;
            case EVENT :
                handleEvent(inputData, outputFormatter);
                break;
            case UNKNOWN :
                handleUnknown(outputFormatter);
                break;
        }
        return outputFormatter.getFormattedOutput();
    }

    private void handleAdd(String inputData, OutputFormatter outputFormatter) {
        this.taskList.addTask(Task.getInstance(inputData));
        outputFormatter.appendAll("added: ", inputData);
    }

    private void handleMark(String inputData, OutputFormatter outputFormatter) {
        String[] inputSequenceSeparated = inputData.split(" ");
        Task taskToBeMarked = this.taskList.getTaskByTaskId(parseInt(inputSequenceSeparated[1]));
        taskToBeMarked.complete();
        outputFormatter.appendAll("Nice! I've marked this task as done: ", "\n", taskToBeMarked);
    }

    private void handleUnmark(String inputData, OutputFormatter outputFormatter) {
        String[] inputSequenceSeparated = inputData.split(" ");
        Task taskToBeUnmarked = this.taskList.getTaskByTaskId(parseInt(inputSequenceSeparated[1]));
        taskToBeUnmarked.markAsNotComplete();
        outputFormatter.appendAll("OK, I've marked this task as not done yet: ", "\n", taskToBeUnmarked);
    }

    private void handleList(OutputFormatter outputFormatter) {
        outputFormatter.appendAll(this.taskList.formattedOutput());
    }

    private void handleToDo(String inputData, OutputFormatter outputFormatter) {
        String[] inputSequenceSeparated = inputData.split(" ");
        taskList.addTask(ToDo.getInstance(inputSequenceSeparated[1]));
        outputFormatter.appendAll("Got it. I've added this task: ", "\n", taskList.getTaskByIndex(taskList.getLength() - 1), "\n", "Now you have ", taskList.getLength(), " task(s) in the list.");
    }

    private void handleDeadline(String inputData, OutputFormatter outputFormatter) {
        String deadlineBy = inputData.split("/by ")[1];
        String description = inputData.split(" /by")[0].split("deadline ")[0];
        taskList.addTask(Deadline.getInstance(description, deadlineBy));
        outputFormatter.appendAll("Got it. I've added this task: ", "\n", taskList.getTaskByIndex(taskList.getLength() - 1), "\n", "Now you have ", taskList.getLength(), " task(s) in the list.");
    }

    private void handleEvent(String inputData, OutputFormatter outputFormatter) {
        String eventAt = inputData.split("/at ")[1];
        String description = inputData.split(" /at")[0].split("event ")[0];
        taskList.addTask(Event.getInstance(description, eventAt));
        outputFormatter.appendAll("Got it. I've added this task: ", "\n", taskList.getTaskByIndex(taskList.getLength() - 1), "\n", "Now you have ", taskList.getLength(), " task(s) in the list.");
    }

    private void handleUnknown(OutputFormatter outputFormatter) {
        outputFormatter.appendAll("This is an unknown command");
    }

}
