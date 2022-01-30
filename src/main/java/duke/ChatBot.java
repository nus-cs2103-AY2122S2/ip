package duke;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.data.Storage;
import duke.data.TaskList;
import duke.dukeexceptions.DukeException;
import duke.parser.Parser;
import duke.responses.Response;
import duke.ui.Ui;

/**
 * This is the chatbot object.
 */
public class ChatBot {
  private TaskList taskList;
  private Storage store;
  private Parser commandHandler;
  private Ui cmdLine;
  private boolean isRunning = true;

  /**
   * Constructs the ChatBot
   */
  ChatBot() {
    this.taskList = new TaskList();
    this.store = new Storage(taskList);
    this.commandHandler = new Parser();
    this.cmdLine = new Ui();
  }

  /**
   * Starts the chatbot
   */
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
