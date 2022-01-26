package com.duke.command;

import com.duke.exception.DukeEmptyListException;
import com.duke.exception.DukeInvalidArgumentException;
import com.duke.modules.Storage;
import com.duke.modules.TaskList;
import com.duke.tasks.Task;

import java.io.IOException;

public class CommandUnmark extends Command {
    private String input;
    private TaskList taskList;

    public CommandUnmark(String input, TaskList taskList) {
        super();
        this.input = input;
        this.taskList = taskList;
    }

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
