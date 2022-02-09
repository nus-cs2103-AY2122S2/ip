package stevie.parser.types.add;

import stevie.command.AddCommand;
import stevie.command.Command;
import stevie.exception.ParserException;
import stevie.exception.TaskException;
import stevie.exception.messages.ParserExceptionMessages;
import stevie.task.TaskCreator;
import stevie.task.TaskType;

public class TodoTaskParser extends TaskParser {
    private static final String regexString = "^(.*?)";

    public TodoTaskParser(String input) {
        super(input, regexString);
    }

    @Override
    public Command parse() throws ParserException, TaskException {
        if (!validateInput()) {
            throw new ParserException(ParserExceptionMessages.AddTodoParseError);
        }
        String name = input.trim();
        if (!isNotEmpty(name)) {
            throw new ParserException(ParserExceptionMessages.AddTodoParseError);
        }
        return new AddCommand(TaskCreator.create(TaskType.Todo, false, name));
    }
}
