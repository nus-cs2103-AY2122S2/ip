package dooke.core;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;

import dooke.core.exceptions.EmptyArgumentException;
import dooke.core.exceptions.FileIsCorruptException;
import dooke.core.exceptions.InvalidDeleteIndexException;
import dooke.core.exceptions.NoDeadlineMentionedException;
import dooke.core.exceptions.NoDescriptionGivenException;
import dooke.core.exceptions.NoEventLocaleMentionedException;
import dooke.core.exceptions.NoTaskToDeleteException;
import dooke.core.exceptions.TaskAlreadyDoneException;
import dooke.core.exceptions.TaskAlreadyUnmarkedException;
import dooke.core.exceptions.ToDoEmptyException;
import dooke.core.tasks.Deadline;
import dooke.core.tasks.Event;
import dooke.core.tasks.Task;
import dooke.core.tasks.TaskList;
import dooke.core.tasks.ToDo;
import dooke.utilities.OutputFormatter;

/**
 * Handles the input commands.
 *
 * @author s7manth
 * @version 0.2
 */
public class InputHandler {
    private TaskList taskList;

    /**
     * Constructor for the InputHandler class.
     */
    private InputHandler() {
        this.taskList = TaskList.getInstance();
    }

    /**
     * Factory method to obtain an instance of InputHandler.
     *
     * @return An instance of InputHandler.
     */
    public static InputHandler getInstance() {
        return new InputHandler();
    }

    /**
     * Handles the input given the string representation of the input command by the user.
     *
     * @param inputData Input command to be handled.
     * @return The output string.
     */
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
        case FIND :
            handleFind(inputData, outputFormatter);
            break;
        case UNKNOWN :
            handleUnknown(outputFormatter);
            break;
        default :
            break;
        }
        return outputFormatter.getFormattedOutput();
    }

    /**
     * Handles the mark command.
     *
     * @param inputData Input command to be handled.
     * @param outputFormatter The output formatter used to produce desired output.
     */
    private void handleMark(String inputData, OutputFormatter outputFormatter) {
        try {
            String[] inputSequenceSeparated = inputData.split(" ");
            Task taskToBeMarked = this.taskList.getTaskByIndex(parseInt(inputSequenceSeparated[1]) - 1);
            taskToBeMarked.complete();
            outputFormatter.appendAll("Nice! I've marked this task as done:", "\n", taskToBeMarked);
        } catch (NumberFormatException e) {
            outputFormatter.append("This is not a valid task number!");
        } catch (IndexOutOfBoundsException | TaskAlreadyDoneException e) {
            outputFormatter.append(e.getMessage());
        }

    }

    /**
     * Handles the unmark command.
     *
     * @param inputData Input command to be handled.
     * @param outputFormatter The output formatter used to produce desired output.
     */
    private void handleUnmark(String inputData, OutputFormatter outputFormatter) {
        try {
            String[] inputSequenceSeparated = inputData.split(" ");
            Task taskToBeUnmarked = this.taskList.getTaskByIndex(parseInt(inputSequenceSeparated[1]) - 1);
            taskToBeUnmarked.markAsNotComplete();
            outputFormatter.appendAll("OK, I've marked this task as not done yet:", "\n", taskToBeUnmarked);
        } catch (NumberFormatException e) {
            outputFormatter.append("This is not a valid task number!");
        } catch (IndexOutOfBoundsException | TaskAlreadyUnmarkedException e) {
            outputFormatter.append(e.getMessage());
        }
    }

    /**
     * Handles the list command.
     *
     * @param outputFormatter The output formatter used to produce desired output.
     */
    private void handleList(OutputFormatter outputFormatter) {
        outputFormatter.appendAll(this.taskList.formattedOutput());
    }

    /**
     * Handles the todo command.
     *
     * @param inputData Input command to be handled.
     * @param outputFormatter The output formatter used to produce desired output.
     */
    private void handleToDo(String inputData, OutputFormatter outputFormatter) {
        try {
            String description = inputData.substring(4).trim();
            taskList.addTask(ToDo.getInstance(description));
            outputFormatter.appendAll("Got it. I've added this task:", "\n",
                    taskList.getTaskByIndex(taskList.getLength() - 1), "\n", "Now you have ", taskList.getLength(),
                    " task(s) in the list.");
        } catch (ToDoEmptyException e) {
            outputFormatter.append(e.getMessage());
        }
    }

    /**
     * Performs checks to the task parameters and initializes them.
     *
     * @param separator The input command in separated form.
     * @return The string array containing the initialized variables.
     */
    private String[] performChecksAndInitialize(String[] separator) {
        String[] output = new String[2];
        if (separator.length == 2) {
            output[0] = separator[1].trim();
            output[1] = separator[0].trim();
        } else if (separator.length == 1) {
            output[0] = "";
            output[1] = separator[0].trim();
        } else {
            output[0] = "";
            output[1] = "";
        }
        return output;
    }

    /**
     * Handles the deadline command.
     *
     * @param inputData Input command to be handled.
     * @param outputFormatter The output formatter used to produce desired output.
     */
    private void handleDeadline(String inputData, OutputFormatter outputFormatter) {
        try {
            String deadlineBy;
            String description;
            String[] inputSequence = inputData.split("deadline ");

            if (inputSequence.length > 1) {
                String[] bySeparator = inputSequence[1].split("/by");
                String[] output = performChecksAndInitialize(bySeparator);
                assert output.length == 2;
                deadlineBy = output[0];
                description = output[1];
            } else {
                deadlineBy = "";
                description = "";
            }
            taskList.addTask(Deadline.getInstance(description, deadlineBy));
            outputFormatter.appendAll("Got it. I've added this task:", "\n",
                    taskList.getTaskByIndex(taskList.getLength() - 1), "\n", "Now you have ", taskList.getLength(),
                    " task(s) in the list.");
        } catch (NoDescriptionGivenException | NoDeadlineMentionedException e) {
            outputFormatter.append(e.getMessage());
        } catch (DateTimeParseException e) {
            outputFormatter.append("The date format is invalid. The accepted format is yyyy-mm-dd and/or hh:mm");
        }
    }

    /**
     * Handles the event command.
     *
     * @param inputData Input command to be handled.
     * @param outputFormatter The output formatter used to produce desired output.
     */
    private void handleEvent(String inputData, OutputFormatter outputFormatter) {
        try {
            String eventAt;
            String description;
            String[] inputSequence = inputData.split("event ");

            if (inputSequence.length > 1) {
                String[] atSeparator = inputSequence[1].split("/at");
                String[] output = performChecksAndInitialize(atSeparator);
                assert output.length == 2;
                eventAt = output[0];
                description = output[1];
            } else {
                eventAt = "";
                description = "";
            }
            taskList.addTask(Event.getInstance(description, eventAt));
            outputFormatter.appendAll("Got it. I've added this task:", "\n",
                    taskList.getTaskByIndex(taskList.getLength() - 1), "\n", "Now you have ", taskList.getLength(),
                    " task(s) in the list.");
        } catch (NoDescriptionGivenException | NoEventLocaleMentionedException e) {
            outputFormatter.append(e.getMessage());
        } catch (DateTimeParseException e) {
            outputFormatter.append("The date format is invalid. The accepted format is yyyy-mm-dd and/or hh:mm");
        }
    }

    /**
     * Handles any unknown command.
     *
     * @param outputFormatter The output formatter used to produce desired output.
     */
    private void handleUnknown(OutputFormatter outputFormatter) {
        outputFormatter.appendAll("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Handles the delete command.
     *
     * @param inputData Input command to be handled.
     * @param outputFormatter The output formatter used to produce desired output.
     */
    private void handleDelete(String inputData, OutputFormatter outputFormatter) {
        try {
            String[] indexRetriever = inputData.split("delete ");
            Task toBeDeleted;
            if (indexRetriever.length <= 1) {
                toBeDeleted = this.taskList.deleteTask("");
            } else {
                toBeDeleted = this.taskList.deleteTask(indexRetriever[1]);
            }
            outputFormatter.appendAll("Noted. I've removed this task:", "\n", toBeDeleted, "\n",
                    "Now you have ", taskList.getLength(), " task(s) in the list.");

        } catch (NumberFormatException e) {
            outputFormatter.append("Please provide a number index to delete!");
        } catch (InvalidDeleteIndexException | NoTaskToDeleteException e) {
            outputFormatter.append(e.getMessage());
        }
    }

    /**
     * Handles the find command.
     *
     * @param inputData Input command to be handled.
     * @param outputFormatter The output formatter used to produce desired output.
     */
    private void handleFind(String inputData, OutputFormatter outputFormatter) {
        try {
            String[] inputSequence = inputData.split("find");
            if (inputSequence.length < 2) {
                throw new EmptyArgumentException();
            }
            String toBeFound = inputSequence[1].trim();
            outputFormatter.append(this.taskList.outputWithFoundString(toBeFound));
        } catch (EmptyArgumentException e) {
            outputFormatter.append(e.getMessage());
        }
    }

    /**
     * Reads the file to add to the task list.
     *
     * @param file The file to be read.
     * @throws IOException Throws an exception if not able to read/obtain the file.
     * @throws FileIsCorruptException Throws an exception if the contents of the file are in an incorrect format.
     */
    public void initializeWithFile(File file) throws IOException, FileIsCorruptException {
        BufferedReader fileReader = new BufferedReader(new FileReader(file));
        String fileLine = fileReader.readLine();
        while (fileLine != null) {
            handleFileInput(fileLine);
            fileLine = fileReader.readLine();
        }
    }

    /**
     * Handles the inputs received from the persistent storage txt file.
     *
     * @param inputData Input command to be handled.
     * @throws FileIsCorruptException Throws an exception if the contents of the file are in an incorrect format.
     */
    public void handleFileInput(String inputData) throws FileIsCorruptException {
        String[] separatedInputSequence = inputData.split(" \\| ");
        boolean isMarked = separatedInputSequence[1].equals("X");
        int lengthOfInputSequence = separatedInputSequence.length;

        switch (separatedInputSequence[0]) {
        case "T":
            if (lengthOfInputSequence != 3) {
                throw new FileIsCorruptException();
            }
            this.taskList.addTask(ToDo.getInstance(separatedInputSequence[2]));
            break;
        case "D":
            if (lengthOfInputSequence != 4) {
                throw new FileIsCorruptException();
            }
            this.taskList.addTask(Deadline.getInstance(separatedInputSequence[2], separatedInputSequence[3]));
            break;
        case "E":
            if (lengthOfInputSequence != 4) {
                throw new FileIsCorruptException();
            }
            this.taskList.addTask(Event.getInstance(separatedInputSequence[2], separatedInputSequence[3]));
            break;
        default :
            break;
        }

        if (isMarked) {
            this.taskList.getTaskByIndex(this.taskList.getLength() - 1).complete();
        }
    }

    /**
     * Saves the tasks at the end of the session.
     *
     * @param filePath The path of the file where the content needs to be saved.
     * @throws IOException Throws an exception in the event that there is no file to be written to.
     */
    public void saveToFile(String filePath) throws IOException {
        File file = new File(filePath);
        boolean hasCreatedDirectory = file.getParentFile().mkdirs();
        boolean hasCreatedFile = file.createNewFile();
        assert file.exists();
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.flush();
        writer.write(this.taskList.exportFileOutput());
        writer.close();
    }

}
