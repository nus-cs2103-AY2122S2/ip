package bernie.commands;

import bernie.parser.Parser;
import bernie.storage.Storage;
import bernie.tasks.TaskList;
import bernie.ui.UiHandler;

public abstract class Command {
     TaskList tasks;
     UiHandler uiHandler;
     Storage storage;
     Parser parser;
     String input;

     public Command(TaskList tasks, UiHandler uiHandler, Storage storage, Parser parser, String input) {
          this.tasks = tasks;
          this.uiHandler = uiHandler;
          this.storage = storage;
          this.parser = parser;
          this.input = input;
     }

     public abstract void execute();
}
