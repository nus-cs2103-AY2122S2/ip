package com.duke.command;

import com.duke.modules.TaskList;

/**
 * Represents a chatbot command for shutting down the chatbot.
 */
public class CommandBye extends Command {
  private String input;

  /**
   * Constructor for this class.
   * @param input The string of arguments entered by the user, excluding the command word.
   */
  public CommandBye(String input) {
    super();
    this.input = input;
  }

  /**
   * Returns a string of the result of executing the intended function of this class.
   * This string is wrapped in a CommandResult object
   * @return A CommandResult object containing the result message.
   */
  @Override
  public CommandResult execute() {
    if (input.length() != 0) {
      return new CommandResult("A bye command should not be succeeded by any words. Did you meant to do something else?");
    } else {
      return CommandResult.shutdownResult();
    }
  }
}
