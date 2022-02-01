package com.duke.modules;

import com.duke.command.CommandAdd;
import com.duke.command.CommandBye;
import com.duke.command.CommandClear;
import com.duke.command.CommandDelete;
import com.duke.command.CommandFind;
import com.duke.command.CommandList;
import com.duke.command.CommandMark;
import com.duke.command.CommandResult;
import com.duke.command.CommandUnmark;

/**
 * A class responsible for parsing user inputs.
 */
public class Parser {
    private TaskList taskList;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Parses the first word of the user input, and executes the corresponding command.
     * @param string String of user input.
     * @return A command result from the execution of the command.
     */
    public CommandResult parse(String string) {
        CommandResult cmdResult = null;
        String[] strArr = string.split(" ");
        String str = "";
        if (strArr.length != 1) {
            str = string.substring(string.indexOf(" ") + 1);
        }
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
