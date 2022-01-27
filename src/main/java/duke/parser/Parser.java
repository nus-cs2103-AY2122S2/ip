package duke.parser;

import duke.exception.ChiException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;
import duke.task.Event;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Interprets messages sent by the user.
 */
public class Parser {

    /**
     * Processes a Task index based on message sent by user.
     *
     * @param msg The message sent by the user.
     * @param size The current size of the TaskList.
     * @return An integer of the index.
     * @throws ChiException If invalid messages are sent by the user.
     */
    private int processNumberMsg(String msg, int size) throws ChiException {
        String refine = msg.trim();
        if (refine.split(" ").length > 1) {
            throw new ChiException("Too many words nyan!");
        } else {
            try {
                int index = Integer.parseInt(refine);
                if (index > size) {
                    throw new ChiException("Too big index nyan!");
                } else if (index < 0) {
                    throw new ChiException("No negative indexes nyan!");
                }
                return index - 1;
            } catch (NumberFormatException e) {
                throw new ChiException("This is not a number nyan!");
            }
        }
    }

    /**
     * Adds a new Deadline task if message sent by user is given in the correct format.
     *
     * @param msg The body of the message sent by the user.
     * @param tl The TaskList where the Deadline will be stored.
     * @param sge The Storage which will write the new Deadline onto the data file.
     * @return A String representing a successful Deadline addition.
     * @throws ChiException If an invalid message is read.
     * @throws IOException If problem occurs when writing the new Deadline onto the data file.
     */
    private String processDeadlineMsg(String msg, TaskList tl, Storage sge) throws ChiException, IOException {
        String[] refineMore = msg.split("/by");
        System.out.println(refineMore[0]);
        if (refineMore[0].equals(msg)) {
            throw new ChiException("Please include /by separator nyan!");
        }
        if (refineMore[0].trim().equals("")) {
            throw new ChiException("deadline");
        }
        if (refineMore.length == 1) {
            throw new ChiException("Please include a date time nyan!");
        }
        if (refineMore.length > 2) {
            throw new ChiException("Too many /by-s nyannn!!!");
        }
        Task newTask1;
        try {
            LocalDate d = LocalDate.parse(refineMore[1].trim().split(" ")[0].trim(),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            if (refineMore[1].trim().split(" ").length == 2) {
                LocalTime t = LocalTime.parse(refineMore[1].trim().split(" ")[1].trim(),
                        DateTimeFormatter.ofPattern("HH:mm"));
                newTask1 = new Deadline(refineMore[0].trim(), d, t, false);

            } else if (refineMore[1].trim().split(" ").length == 1) {
                newTask1 = new Deadline(refineMore[0].trim(), d, false);
            } else {
                throw new ChiException("Too many date times nyan!!");
            }
            tl.addTask(newTask1);
            sge.updateFile(newTask1, tl, "deadline");
        } catch (DateTimeParseException e) {
            throw new ChiException("This date time format is not recognized nyan!");
        }
        return String.format("Ok! Chi-san has added:\n%s\nYou have %d tasks nyan~!\n",
                newTask1, tl.getSize());

    }

    /**
     * Adds a new Event task if message sent by user is given in the correct format.
     *
     * @param msg The body of the message sent by the user.
     * @param tl The TaskList where the Event will be stored.
     * @param sge The Storage which will write the new Event onto the data file.
     * @return A String representing a successful Event addition.
     * @throws ChiException If an invalid message is read.
     * @throws IOException If problem occurs when writing the new Event onto the data file.
     */
    private String processEventMsg(String msg, TaskList tl, Storage sge) throws ChiException, IOException {
        String[] refineMore = msg.split("/at");
        if (refineMore[0].equals(msg)) {
            throw new ChiException("Please include /at separator nyan!");
        }
        if (refineMore[0].trim().equals("")) {
            throw new ChiException("event");
        }
        if (refineMore.length == 1) {
            throw new ChiException("Please write a timing nyan!");
        }
        if (refineMore.length > 2) {
            throw new ChiException("Too many /at-s nyan!");
        }
        Task newTask;
        try {
            LocalDate d = LocalDate.parse(refineMore[1].trim().split(" ")[0].trim(),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            if (refineMore[1].trim().split(" ").length == 2) {
                if (refineMore[1].trim().split(" ")[1].trim().split("-").length == 0) {
                    throw new ChiException("No timing found nyan1");
                } else if (refineMore[1].trim().split(" ")[1].trim().split("-").length == 1) {
                    throw new ChiException("1 time is missing!");
                } else if (refineMore[1].trim().split(" ")[1].trim().split("-").length > 2) {
                    throw new ChiException("Too many timings!");
                }
                LocalTime t1 = LocalTime.parse(refineMore[1].trim().split(" ")[1].trim().split("-")[0],
                        DateTimeFormatter.ofPattern("HH:mm"));
                LocalTime t2 = LocalTime.parse(refineMore[1].trim().split(" ")[1].trim().split("-")[1],
                        DateTimeFormatter.ofPattern("HH:mm"));
                if (t1.isAfter(t2)) {
                    throw new ChiException("The end time cannot come before start time nyan!");
                }
                newTask = new Event(refineMore[0].trim(), d, t1, t2, false);
            } else if (refineMore[1].trim().split(" ").length == 1) {
                newTask = new Event(refineMore[0].trim(), d, false);
            } else {
                throw new ChiException("Too many date time things nyan!");
            }
            tl.addTask(newTask);
            sge.updateFile(newTask, tl, "event");
        } catch (DateTimeParseException e) {
            throw new ChiException("This date time format is not recognized nyan!!");
        }
        return String.format("Ok! Chi-san has added:\n%s\nYou have %d tasks nyan~!\n",
                newTask, tl.getSize());
    }

    /**
     * Finds tasks in the task list which match keywords in the message body.
     *
     * @param msg The message body.
     * @param tl The TaskList containing tasks.
     * @return A String response of matching tasks found.
     */
    public String processFindMsg(String msg, TaskList tl) {
        // Assume that at this point there are some text
        String[] findWords = msg.split(" ");
        return "Here's what I could find!\n" + tl.checkWordsInTask(findWords);
    }

    /**
     * Carries out various operations if valid message is sent by the user.
     *
     * @param msg The raw message sent by the user.
     * @param tl The TaskList storing the user's Tasks.
     * @param sge Storage of the tasks in the hard-disk.
     * @return A string of the expected response to the user's message.
     * @throws ChiException If the message cannot be interpreted.
     * @throws IOException If there are problems accessing the data file in Storage.
     */
    public String processMessage(String msg, TaskList tl, Storage sge) throws ChiException, IOException {
        // Obtain 1st word
        String[] command = msg.trim().split(" ");
        if (command.length == 1) {
            if (command[0].equals("list")) {
                return tl.getTasksMsg();
            }
            // Stretch goals command class storing commands HashMap to search and return them
            if (command[0].toLowerCase().equals("deadline") || command[0].toLowerCase().equals("event") ||
                    command[0].toLowerCase().equals("todo")) {
                throw new ChiException(command[0].toLowerCase());
            }
            // Unknown message, or command lacks task
            throw new ChiException("The following command " + msg.trim().toLowerCase() + " cannot be understood");
        } else {
            // Check for keywords
            int processed;
            switch (command[0].toLowerCase()) {
            case "mark":
                // Retrieve the task from the list
                processed = processNumberMsg(msg.substring(4), tl.getSize());
                Task doneTask = tl.getTask(processed);
                // Mark as done
                doneTask.markAsDone();
                sge.updateFile(doneTask, tl, "mark");
                return String.format("Great job nyan~!\n%s\n", doneTask);
                // Fallthrough
            case "unmark":
                processed = processNumberMsg(msg.substring(6), tl.getSize());
                Task doneTask1 = tl.getTask(processed);
                doneTask1.markAsUndone();
                sge.updateFile(doneTask1, tl, "unmark");
                return String.format("Let's get it done next time nyan~!\n%s\n", doneTask1);
                // Fallthrough
            case "todo":
                // Obtain the ToDo
                Task newTask = new Todo(msg.substring(4).trim(), false);
                // Add task to list
                tl.addTask(newTask);
                sge.updateFile(newTask, tl, "todo");
                return String.format("Ok! Chi-san has added:\n%s\nYou have %d tasks nyan~!\n",
                        newTask, tl.getSize());
                // Fallthrough
            case "deadline":
                return processDeadlineMsg(msg.substring(8), tl, sge);
                // Fallthrough
            case "event":
                return processEventMsg(msg.substring(5), tl, sge);
                // Fallthrough
            case "delete":
                processed = processNumberMsg(msg.substring(6), tl.getSize());
                Task toDelete = tl.getTask(processed);
                tl.deleteTask(toDelete);
                sge.updateFile(toDelete, tl, "delete");
                return String.format("Chi-san has removed task:\n %s\nYou now have %d tasks nyan~!\n",
                        toDelete, tl.getSize());
                // Fallthrough
            case "find":
                return processFindMsg(msg.substring(4), tl);
                // Fallthrough
            default:
                // Some message which does not start with a keyword
                throw new ChiException(msg);
                // Fallthrough
            }
        }
    }
}
