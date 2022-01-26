package com.duke.command;

import com.duke.modules.Storage;
import com.duke.modules.TaskList;

import java.io.IOException;

/**
 * Represents a chatbot command for clearing all Tasks from the TaskList
 */
public class CommandClear extends Command {
  private String input;
  private TaskList taskList;

  /**
   * Constructor for this class.
   * @param input The string of arguments entered by the user, excluding the command word.
   * @param taskList The tasklist meant for the Tasks to be cleared from.
   */
  public CommandClear(String input, TaskList taskList) {
    super();
    this.input = input;
    this.taskList = taskList;
  }

  /**
   * Returns a string of the result of executing the intended function of this class.
   * This string is wrapped in a CommandResult object
   * @return A CommandResult object containing the result message.
   */
  @Override
  public CommandResult execute() {
    try {
      if (input.length() != 0) {
        return new CommandResult("A clearls command should not be succeeded by any words. Did you meant to do something else?");
      } else {
        taskList.clearList();
        return new CommandResult("List successfully cleared");
      }
    } catch (IOException e) {
      return new CommandResult("Unable to save list." +
          "Please check if you have permission to write to files in the following directory: " +
          Storage.getInstance().getDirectoryPath());
    }
  }
}
