package juke.common;

import juke.command.Command;
import juke.command.CommandHandler;

public class Parser {
    
    public static Command parse(String string) {
        return CommandHandler.COMMANDS.get(string).get();
    }
}
