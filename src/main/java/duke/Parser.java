package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Parses commands from users and also commands read from tasks.txt
 */
public class Parser {

    /**
     * Parses commands read from tasks.txt to create appropriate task objects
     * @param details an array of Strings that contain all the relevant information about the command.
     * @return currTask, the appropriate Task object for the commands.
     */
    public static Task parseCommandFromFile(String[] details) {
        details[0] = details[0].trim();
        int status = Integer.parseInt(details[1].trim());
        Task currTask;
        if (details[0].equals("T")) {
            currTask = new Todo(details[2].trim());
        } else if (details[0].equals("D")) {
            currTask = new Deadline(details[2].trim(), details[3].trim());
        } else {
            String eventInformation = details[3];
            int indexOfDifferentiator = eventInformation.indexOf("-");
            String dateAndStartTime = eventInformation.substring(0, indexOfDifferentiator).trim();
            String endTime = eventInformation.substring(indexOfDifferentiator + 1).trim();
            currTask = new Event(details[2].replaceAll("^\\s+", ""), dateAndStartTime, endTime);
        }

        if (status == 1) {
            currTask.markDone();
        }

        return currTask;
    }

    /**
     * Parses commands inputted by the user to create appropriate task objects
     * @param taskType, a String, that indicates which particular Task object needs to be created.
     * @param instruction contains the description of the task alongside the date and time(situational).
     * @return taskParsed, the appropriate Task object for the commands.
     * @throws CustomException
     */
    public static Task parseCommandFromUser(String taskType, String instruction) throws CustomException {
        Task taskParsed;
        if (taskType.equals("todo")) {
            String description = instruction.substring(4);
            if (description.isBlank()) {
                throw new CustomException("todo description cannot be blank! Please add task details");
            } else {
                taskParsed = new Todo(description.substring(1));
            }
        } else if (taskType.equals("deadline")) {
            int index = instruction.indexOf("/by");
            if (index != -1) {
                String description = instruction.substring(9, index);
                String time = instruction.substring(index + 4);
                taskParsed = new Deadline(description, time);
            } else {
                throw new CustomException("Incorrect time format: ensure to prefix time with '/by'");
            }
        } else {
            int index = instruction.indexOf("/at");
            if (index != -1) {
                String description = instruction.substring(6, index);
                String time = instruction.substring(index + 4);
                int indexOfDifferentiator = time.indexOf("-");
                String dateAndStartTime = time.substring(0, indexOfDifferentiator).trim();
                String endTime = time.substring(indexOfDifferentiator + 1).trim();
                taskParsed = new Event(description, dateAndStartTime, endTime);
            } else {
                throw new CustomException("Incorrect time format: ensure to prefix time with '/at'");
            }

        }
        return taskParsed;
    }

    /**
     * Checks if the command is meant to be understood by the program
     * @param taskType, a String, that indicates command type
     * @return boolean.
     */
    public Boolean isValidCommand(String taskType) {
        return taskType.equals("todo") || taskType.equals("deadline")
                || taskType.equals("event");
    }
}
