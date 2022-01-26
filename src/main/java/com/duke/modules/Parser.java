package com.duke.modules;

import com.duke.command.*;

/**
 * A class responsible for parsing user inputs.
 */
public class Parser {
  private TaskList taskList;

  public Parser(TaskList taskList) {
    this.taskList = taskList;
  }


  /**
   * Parses the string given, and executes commands based on the command keyword detected.
   * @param string The user input string given.
   * @return A CommandResult containing the result message of the execution of the command.
   */
  public CommandResult parse(String string) {
    CommandResult cmdResult = null;
    String[] strArr = string.split(" ");
    String str = "";
    if (strArr.length != 1) str = string.substring(string.indexOf(" ") + 1);
    switch (strArr[0]) {
      case "":
        cmdResult = new CommandResult("*crickets*");
        break;
      case "bye":
        cmdResult = new CommandBye(str).execute();
        break;
      case "list":
        cmdResult = new CommandList(str, taskList).execute();
        break;
      case "mark":
        cmdResult = new CommandMark(str, taskList).execute();
        break;
      case "unmark":
        cmdResult = new CommandUnmark(str, taskList).execute();
        break;
      case "delete":
        cmdResult = new CommandDelete(str, taskList).execute();
        break;
      case "clearls":
        cmdResult = new CommandClear(str, taskList).execute();
        break;
      case "find":
        cmdResult = new CommandFind(str, taskList).execute();
        break;
      case "todo":
      case "deadline":
      case "event":
        cmdResult = new CommandAdd(strArr[0], str, taskList).execute();
        break;
      default:
        cmdResult = CommandResult.unknownResult();
    }
    return cmdResult;
  }
}
