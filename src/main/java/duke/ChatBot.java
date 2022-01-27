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
  
  public void run() {
    
      store.initialiseStorage();
      this.taskList = store.loadFromDisk();
      cmdLine.callResponse(commandHandler.start());
      while(isRunning) {
        try {
          String stringCmd = cmdLine.getNextLine();
          Command cmd = commandHandler.getCommand(stringCmd);
          cmd.getResources(store, taskList);
          if (cmd instanceof ByeCommand) {
            isRunning = false;
          }
          Response feedback = cmd.execute();
          cmdLine.callResponse(feedback);
        } catch (DukeException e) {
          e.callback();
        }
      }

  }


  /**
   * Method that stops the Chatbot;
   */
  public void stop() {
    this.isRunning = false;
  }
}
