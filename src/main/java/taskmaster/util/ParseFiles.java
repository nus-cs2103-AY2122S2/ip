package taskmaster.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import taskmaster.exception.TaskmasterExceptions;
import taskmaster.task.DeadlineTask;
import taskmaster.task.EventTask;
import taskmaster.task.Task;
import taskmaster.task.TodoTask;

/**
 *  This class encapsulates a parser that helps parse
 *  information read from the text file.
 *
 */

public class ParseFiles {

    /**
     * Helper function that breaks the string into components.
     * @param str The string that is going to be broken up into
     *             components.
     *
     * @return  An array of strings.
     */

    private String[] splitStringIntoParts(String str) {
        //Split the string based on the delimiter which is "|" to make identifying each component easier.
        String[] splitBasedOnDelimiter = str.split("\\|");
        for (int i = 0; i < splitBasedOnDelimiter.length; i++) {
            splitBasedOnDelimiter[i] = splitBasedOnDelimiter[i].trim();
        }
        return splitBasedOnDelimiter;
    }

    /**
     * Returns true if mark number is 1 and false otherwise.
     *
     * @param markNumber the mark number based on file read.
     *
     * @return true if mark number is 1.
     */

    private boolean isMark(int markNumber) {
        return (markNumber == 1);
    }

    /**
     * Helper function that helps handle event tasks.
     * Creates an eventTask object based on the information
     * that has been parsed in.
     *
     * @param isMark Determines whether task is completed (0: Not completed 1: Completed).
     *
     * @param description description of the task.
     *
     * @param eventAt Date and time of event.
     *
     * @return the newly created event task.
     */

    private Task handlesEventTasks(boolean isMark, String description, LocalDateTime eventAt) {
        EventTask eventTask = new EventTask(description, eventAt);
        if (isMark) {
            eventTask.markTask();
        }
        return eventTask;
    }

    /**
     * Helper function that helps handle deadline tasks.
     * Creates an DeadlineTask object based on the information
     * that has been parsed in.
     *
     * @param isMark Determines whether task is completed (0: Not completed 1: Completed).
     *
     * @param description description of the task.
     *
     * @param deadlineBy Date and time of deadline.
     *
     * @return the newly created deadline task.
     */

    private Task handlesDeadlineTasks(boolean isMark, String description, LocalDateTime deadlineBy) {
        DeadlineTask deadlineTask = new DeadlineTask(description, deadlineBy);
        if (isMark) {
            deadlineTask.markTask();
        }
        return deadlineTask;
    }

    /**
     * Helper function that helps handle To do tasks.
     * Creates a Todo Task object based on the information
     * that has been parsed in.
     *
     * @param isMark Determines whether task is completed (0: Not completed 1: Completed).
     *
     * @param description description of the task.
     *
     * @return the newly created Todo task.
     */

    private Task handlesTodoTasks(boolean isMark, String description) {
        TodoTask todoTask = new TodoTask(description);
        if (isMark) {
            todoTask.markTask();
        }
        return todoTask;
    }


    /**
     * Reads each line from the file and categorizes
     * the task based on their respective task type.
     *
     * @param str the task in saved text format.
     *
     * @return task that has been parsed.
     *
     * @throws TaskmasterExceptions if unable to read the task.
     */
    public Task parseTask(String str) throws TaskmasterExceptions {
        //Container file for returning Task
        Task taskToBeReturned = null;
        //Identify the type of task.
        char taskType = str.charAt(0);
        //Function to help separate the strings into their respective components.
        String[] stringIntoParts = splitStringIntoParts(str);
        try {
            if (taskType == 'T' || taskType == 'D' || taskType == 'E') {
                //Handle Wrong Inputs
                //Case 1: user only specified Task type
                if (stringIntoParts.length == 1) {
                    throw new TaskmasterExceptions("ERROR! You've only specified the task type, you need to specify"
                            + "whether task is completed or not and the description of the task \n");
                }

                //Case 2: If second input(completed/uncompleted) is not an integer, throw exception
                int index = Integer.parseInt(stringIntoParts[1]);

                //Case 3: There is only 2 inputs
                if (stringIntoParts.length == 2) {
                    throw new TaskmasterExceptions("ERROR! You've only specified the task type and whether it is "
                                                + "incomplete or complete \n");
                }

                //Case 4: Second input is not 0 or 1
                if (index != 0 && index != 1) {
                    throw new TaskmasterExceptions("ERROR! Second input has to be 0 or 1!\n");
                }

                //If code reaches here, it is confirmed to have 3 or more parameters
                // Deadline Task or Event Task Handling
                if (taskType == 'D' || taskType == 'E') {
                    //Case 4: Assume that the 3rd input is the description of the task
                    // and the user is missing the deadline by/time of event.
                    if (stringIntoParts.length != 4) {
                        throw new TaskmasterExceptions("ERROR! Deadline and Event tasks is missing the date "
                                                    + "which the event occur on/deadline");
                    } else {
                        String timeAndDate = stringIntoParts[3];
                        DateTimeFormatter oldFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                        LocalDateTime dateTime = LocalDateTime.parse(timeAndDate, oldFormat);
                        if (taskType == 'D') {
                            taskToBeReturned = handlesDeadlineTasks(isMark(index), stringIntoParts[2], dateTime);
                        } else {
                            taskToBeReturned = handlesEventTasks(isMark(index), stringIntoParts[2], dateTime);
                        }
                    }
                } else {
                    //Todo Task
                    taskToBeReturned = handlesTodoTasks(isMark(index), stringIntoParts[2]);
                }
            } else {
                //If Task type is not T/D/E
                throw new TaskmasterExceptions("ERROR! I do not recognise that task type!");
            }

        } catch (NumberFormatException nfe) {
            throw new TaskmasterExceptions("What? Second input has to be an integer! Eg T | 1 | read book,"
                                + " D | 0 | return book | 27/02/1998 1800 \n");

        } catch (DateTimeParseException e) {
            throw new TaskmasterExceptions("ERROR: Date and time is entered in the wrong format eg 27/02/1998 1800");
        }

        return taskToBeReturned;
    }

}
