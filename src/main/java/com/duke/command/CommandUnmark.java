package com.duke.command;

import com.duke.exception.DukeEmptyListException;
import com.duke.exception.DukeInvalidArgumentException;
import com.duke.modules.Storage;
import com.duke.modules.TaskList;
import com.duke.tasks.Task;

import java.io.IOException;

/**
 * Represents a chatbot command for unmarking Tasks in the TaskList
 */
public class CommandUnmark extends Command {
    private String input;
    private TaskList taskList;

    /**
     * Constructor for this class.
     *
     * @param input    The string of arguments entered by the user, excluding the command word.
     * @param taskList The tasklist meant for the Tasks to be added to.
     */
    public CommandUnmark(String input, TaskList taskList) {
        super();
        this.input = input;
        this.taskList = taskList;
    }

    /**
     * Returns a string of the result of executing the intended function of this class.
     * This string is wrapped in a CommandResult object
     *
     * @return A CommandResult object containing the result message.
     */
    @Override
    public CommandResult execute() {
        try {
            return new CommandResult(taskUnmarker(input));
        } catch (DukeInvalidArgumentException | DukeEmptyListException e) {
            return new CommandResult(e.getMessage());
        } catch (NumberFormatException e) {
            return new CommandResult(
                    "Valid numerical number must be given:" + "\nEg: mark 12");
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(
                    "Invalid task chosen, please ensure that the number given is correct");
        } catch (IOException e) {
            return new CommandResult("Unable to save list." +
                    "Please check if you have permission to write to files in the following directory: "
                    + Storage.getInstance().getDirectoryPath());
        }
    }

    /**
     * Finds a task in the specified index of the list, and marks it as not done.
     *
     * @param args The string of arguments entered by the user, excluding the command word.
     * @return A string describing the result.
     * @throws DukeInvalidArgumentException When there are no arguments given.
     * @throws DukeEmptyListException       When length of task list is 0.
     * @throws IOException                  On failure to save list.
     */
    public String taskUnmarker(String args) throws DukeInvalidArgumentException, DukeEmptyListException, IOException {
        if (args.length() < 1) {
            throw new DukeInvalidArgumentException("Please choose which task you would like to unmark.");
        } else if (taskList.getTaskListSize() < 1) {
            throw new DukeEmptyListException("Your list is empty.");
        } else {
            int num = Integer.parseInt(args);
            Task currTask = taskList.getTask(num - 1);
            taskList.unmarkTask(num - 1);
            return String.format("Noted. I've marked this task as not done:\n\t%s", currTask.toString());
        }
    }
}
