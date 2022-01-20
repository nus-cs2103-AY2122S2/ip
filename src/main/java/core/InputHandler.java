package core;

import core.exceptions.*;
import core.tasks.Deadline;
import core.tasks.Event;
import core.tasks.Task;
import core.tasks.ToDo;
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
        InputType inputType = InputIdentifier.determineInputType(inputData);
        OutputFormatter outputFormatter = OutputFormatter.getInstance();

        switch (inputType) {
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
            case DELETE :
                handleDelete(inputData, outputFormatter);
                break;
            case UNKNOWN :
                handleUnknown(outputFormatter);
                break;
        }
        return outputFormatter.getFormattedOutput();
    }


    private void handleMark(String inputData, OutputFormatter outputFormatter) {
        try {
            String[] inputSequenceSeparated = inputData.split(" ");
            Task taskToBeMarked = this.taskList.getTaskByTaskId(parseInt(inputSequenceSeparated[1]));
            taskToBeMarked.complete();
            outputFormatter.appendAll("Nice! I've marked this task as done:", "\n", taskToBeMarked);
        } catch (NumberFormatException e) {
            outputFormatter.append("This is not a valid task number!");
        } catch (IndexOutOfBoundsException | TaskAlreadyDoneException e) {
            outputFormatter.append(e.getMessage());
        }

    }

    private void handleUnmark(String inputData, OutputFormatter outputFormatter) {
        try {
            String[] inputSequenceSeparated = inputData.split(" ");
            Task taskToBeUnmarked = this.taskList.getTaskByTaskId(parseInt(inputSequenceSeparated[1]));
            taskToBeUnmarked.markAsNotComplete();
            outputFormatter.appendAll("OK, I've marked this task as not done yet:", "\n", taskToBeUnmarked);
        } catch (NumberFormatException e) {
            outputFormatter.append("This is not a valid task number!");
        } catch (IndexOutOfBoundsException | TaskAlreadyUnmarkedException e) {
            outputFormatter.append(e.getMessage());
        }
    }

    private void handleList(OutputFormatter outputFormatter) {
        outputFormatter.appendAll(this.taskList.formattedOutput());
    }

    private void handleToDo(String inputData, OutputFormatter outputFormatter) {
        try {
            String description = inputData.substring(4);
            taskList.addTask(ToDo.getInstance(description));
            outputFormatter.appendAll("Got it. I've added this task:", "\n", taskList.getTaskByIndex(taskList.getLength() - 1), "\n", "Now you have ", taskList.getLength(), " task(s) in the list.");
        } catch (ToDoEmptyException e) {
            outputFormatter.append(e.getMessage());
        }
    }

    private void handleDeadline(String inputData, OutputFormatter outputFormatter) {
        try {
            String deadlineBy, description;
            String[] inputSequence = inputData.split("deadline ");

            if (inputSequence.length > 1) {
                String[] bySeparator = inputSequence[1].split("/by");
                if (bySeparator.length == 2) {
                    deadlineBy = bySeparator[1].trim();
                    description = bySeparator[0].trim();
                } else if (bySeparator.length == 1) {
                    deadlineBy = "";
                    description = bySeparator[0].trim();
                } else {
                    deadlineBy = "";
                    description = "";
                }
            } else {
                deadlineBy = "";
                description = "";
            }
            taskList.addTask(Deadline.getInstance(description, deadlineBy));
            outputFormatter.appendAll("Got it. I've added this task:", "\n", taskList.getTaskByIndex(taskList.getLength() - 1), "\n", "Now you have ", taskList.getLength(), " task(s) in the list.");
        } catch (NoDescriptionGivenException | NoDeadlineMentionedException e) {
            outputFormatter.append(e.getMessage());
        }
    }

    private void handleEvent(String inputData, OutputFormatter outputFormatter) {
        try {
            String eventAt, description;
            String[] inputSequence = inputData.split("event ");

            if (inputSequence.length > 1) {
                String[] atSeparator = inputSequence[1].split("/at");
                if (atSeparator.length == 2) {
                    eventAt = atSeparator[1].trim();
                    description = atSeparator[0].trim();
                } else if (atSeparator.length == 1) {
                    eventAt = "";
                    description = atSeparator[0].trim();
                } else {
                    eventAt = "";
                    description = "";
                }
            } else {
                eventAt = "";
                description = "";
            }
            taskList.addTask(Event.getInstance(description, eventAt));
            outputFormatter.appendAll("Got it. I've added this task:", "\n", taskList.getTaskByIndex(taskList.getLength() - 1), "\n", "Now you have ", taskList.getLength(), " task(s) in the list.");
        } catch (NoDescriptionGivenException | NoEventLocaleMentionedException e) {
            outputFormatter.append(e.getMessage());
        }
    }

    private void handleUnknown(OutputFormatter outputFormatter) {
        outputFormatter.appendAll("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    private void handleDelete(String inputData, OutputFormatter outputFormatter) {
        try {
            String[] indexRetriever = inputData.split("delete ");
            Task toBeDeleted;
            if (indexRetriever.length <= 1) {
                toBeDeleted = this.taskList.deleteTask("");
            } else {
                toBeDeleted = this.taskList.deleteTask(indexRetriever[1]);
            }
            outputFormatter.appendAll("Noted. I've removed this task:", "\n", toBeDeleted, "\n", "Now you have ", taskList.getLength(), " task(s) in the list.");

        } catch ( NumberFormatException e) {
            outputFormatter.append("Please provide a number index to delete!");
        } catch (InvalidDeleteIndexException | NoTaskToDeleteException e) {
            outputFormatter.append(e.getMessage());
        }
    }

}
