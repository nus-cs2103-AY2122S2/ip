package main.java.duke;

import main.java.duke.data.Storage;
import main.java.duke.data.TaskList;
import main.java.duke.dukeexceptions.ForeignException;
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
    
      store.initaliseStorage();

      Ui.callResponse(commandHandler.start());

      while(isRunning) {
        try {
          String nextLine = cmdLine.getNextLine();
          Command nextCommand = null;

          nextCommand = cmdLine.getCommand(nextLine);
          Response nextResponse = commandHandler.getResponse(nextCommand, nextLine,store, taskList);
          Ui.callResponse(nextResponse);
          if (nextCommand == Command.bye) {
            break;
          }
        } catch (ForeignException e) {
        e.printStackTrace();
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
