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
        String[] splitBasedOnDelimiter = str.split("\\|");
        for (int i = 0; i < splitBasedOnDelimiter.length; i++) {
            splitBasedOnDelimiter[i] = splitBasedOnDelimiter[i].trim();
        }
        return splitBasedOnDelimiter;
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

    private Task handlesEventTasks(int isMark, String description, LocalDateTime eventAt) {
        EventTask eventTask = new EventTask(description, eventAt);
        if (isMark == 1) {
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

    private Task handlesDeadlineTasks(int isMark, String description, LocalDateTime deadlineBy) {
        DeadlineTask deadlineTask = new DeadlineTask(description, deadlineBy);
        if (isMark == 1) {
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

    private Task handlesTodoTasks(int isMark, String description) {
        TodoTask todoTask = new TodoTask(description);
        if (isMark == 1) {
            todoTask.markTask();
        }
        return todoTask;
    }



    /**
     *  ParseTask reads each line from the file and categorizes
     *  the task based on their respective task type.
     *
     * @param str Line from the file that is to be read.
     *
     * @return Newly created Task.
     */
    public Task parseTask(String str) {
        //Container file for returning Task
        Task taskToBeReturned = null;
        char taskType = str.charAt(0);
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
                            taskToBeReturned = handlesDeadlineTasks(index, stringIntoParts[2], dateTime);
                        } else {
                            taskToBeReturned = handlesEventTasks(index, stringIntoParts[2], dateTime);
                        }
                    }
                } else {
                    //Todo Task
                    taskToBeReturned = handlesTodoTasks(index, stringIntoParts[2]);
                }
            } else {
                //If Task type is not T/D/E
                throw new TaskmasterExceptions("ERROR! I do not recognise that task type!");
            }

        } catch (TaskmasterExceptions e) {
            System.out.println(e.getMessage());

        } catch (NumberFormatException nfe) {
            System.out.println("What? Second input has to be an integer! Eg T | 1 | read book,"
                                + " D | 0 | return book | 27/02/1998 1800 \n");

        } catch (DateTimeParseException e) {
            System.out.println("ERROR: Date and time is entered in the wrong format eg 27/02/1998 1800");
        }

        return taskToBeReturned;
    }

}
