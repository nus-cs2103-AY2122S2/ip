package core;

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
        StringBuilder output = new StringBuilder();

        switch (inputType) {
            case ADD :
                taskList.addTask(Task.getInstance(inputData));
                output.append("added: ");
                output.append(inputData);
                break;
            case LIST :
                output.append(taskList.formattedOutput());
                break;
            case UNKNOWN :
                output.append("This is an unknown command");
                break;
        }
        return output.toString();
    }
}
