package main.java;

import main.java.data.Storage;
import main.java.data.TaskList;
import main.java.dukeexceptions.DukeException;
import main.java.parser.Parser;
import main.java.responses.Response;
import main.java.ui.Ui;

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
          Command nextCommand = cmdLine.getCommand(nextLine);
          Response nextResponse = commandHandler.getResponse(nextCommand, nextLine,store, taskList);
          Ui.callResponse(nextResponse);
          if (nextCommand == Command.bye) {
            break;
          }
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
