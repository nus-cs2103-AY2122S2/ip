package com.duke.command;

public class CommandResult{
  private boolean isShutdown = false;
  private String resultMessage;

  public CommandResult(String resultMessage) {
    this.resultMessage = resultMessage;
  }

  public boolean isShutdown() {
    return isShutdown;
  }

  public String getResultMessage() {
    return resultMessage;
  }

  public static CommandResult shutdownResult() {
    CommandResult result = new CommandResult("LUMU: Goodbye. Hope to see you again soon");
    result.isShutdown = true;
    return result;
  }

  public static CommandResult unknownResult() {
    CommandResult result = new CommandResult("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    return result;
  }
}
