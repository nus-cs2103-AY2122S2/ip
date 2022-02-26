package sparrow.logic.parser;

import sparrow.commons.exceptions.ParseException;
import sparrow.logic.commands.Command;

public interface Parser<T extends Command> {
    T parse(String userInput) throws ParseException;
}
