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
        String[] inputSequenceSeparated = inputData.split(" ");

        switch (inputType) {
            case ADD :
                taskList.addTask(Task.getInstance(inputData));
                outputFormatter.appendAll("added: ", inputData);
                break;
            case MARK :
                Task taskToBeMarked = taskList.getTaskByTaskId(parseInt(inputSequenceSeparated[1]));
                taskToBeMarked.complete();
                outputFormatter.appendAll("Nice! I've marked this task as done: ", "\n", taskToBeMarked);
                break;
            case UNMARK :
                Task taskToBeUnmarked = taskList.getTaskByTaskId(parseInt(inputSequenceSeparated[1]));
                taskToBeUnmarked.markAsNotComplete();
                outputFormatter.appendAll("OK, I've marked this task as not done yet: ", "\n", taskToBeUnmarked);
                break;
            case LIST :
                outputFormatter.appendAll(taskList.formattedOutput());
                break;
            case UNKNOWN :
                outputFormatter.appendAll("This is an unknown command");
                break;
        }
        return outputFormatter.getFormattedOutput();
    }
}
