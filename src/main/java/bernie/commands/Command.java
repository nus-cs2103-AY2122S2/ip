package bernie.commands;

import bernie.parser.Parser;
import bernie.storage.Storage;
import bernie.tasks.TaskList;
import bernie.ui.UiHandler;

/**
 * Command is the abstract class. Any subclass of Command is created in the CommandHandler
 * for execution of the respective command. Command is executed to perform actions based on the
 * input of the user into the program.
 */
public abstract class Command {
     TaskList tasks;
     UiHandler uiHandler;
     Storage storage;
     Parser parser;
     String input;

     /**
      * Constructs a Command Class
      * @param tasks TaskList, contains all our Task we create
      * @param uiHandler UiHandler, responsible for printing out messages to the user for any action done
      * @param storage Storage, responsible for saving and loading of tasks into a text file
      * @param parser Parser, helps to parse user inputs to perform subsequent actions
      * @param input String, user input into the program
      */
     public Command(TaskList tasks, UiHandler uiHandler, Storage storage, Parser parser, String input) {
          this.tasks = tasks;
          this.uiHandler = uiHandler;
          this.storage = storage;
          this.parser = parser;
          this.input = input;
     }

     /** Executes an action */
     public abstract void execute();
}
