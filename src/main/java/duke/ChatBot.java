package main.java.duke;

import main.java.duke.command.ByeCommand;
import main.java.duke.command.Command;
import main.java.duke.data.Storage;
import main.java.duke.data.TaskList;
import main.java.duke.dukeexceptions.DukeException;
import main.java.duke.parser.Parser;
import main.java.duke.responses.Response;
import main.java.duke.ui.Ui;

/**
 * This is the chatbot object.
 */
public class ChatBot {
  TaskList taskList;
  Storage store;
  Parser commandHandler;
  Ui cmdLine;
  
  boolean isRunning = true;
  
  ChatBot() {
    this.taskList = new TaskList();
    this.store = new Storage(taskList);
    this.commandHandler = new Parser();
    this.cmdLine = new Ui();
  }
  
  public void run() throws DukeException {
    
      store.initaliseStorage();
      cmdLine.callResponse(commandHandler.start());
      while(isRunning) {
        String stringCmd = cmdLine.getNextLine();
        Command cmd = commandHandler.getCommand(stringCmd);
        cmd.getReasources(store, taskList);
        if (cmd instanceof ByeCommand) {
          isRunning = false;
        }
        Response feedback = cmd.execute();
        cmdLine.callResponse(feedback);
      }

  }


  /**
   * Method that stops the Chatbot;
   */
  public void stop() {
    this.isRunning = false;
  }
}
