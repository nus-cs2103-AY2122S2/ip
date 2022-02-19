package stevie.parser.types.add;

import stevie.command.AddCommand;
import stevie.command.Command;
import stevie.exception.ParserException;
import stevie.exception.TaskException;
import stevie.exception.messages.ParserExceptionMessages;
import stevie.task.TaskCreator;
import stevie.task.types.TaskType;

/**
 * TodoTaskParser parses a raw string into an AddCommand that adds an TodoTask.
 */
public class TodoTaskParser extends TaskParser {
    private static final String regexString = "^(.*?)";

    /**
     * Constructor for an TodoTaskParser.
     * @param input raw string to be parsed
     */
    public TodoTaskParser(String input) {
        super(input, regexString);
    }

    /**
     * Parses raw string into an AddCommand that adds a TodoTask.
     * @return add command that adds the a TodoTask with the fields
     * @throws ParserException if input of string fails to match with regex
     * @throws TaskException if task creator fails to create task with input fields
     */
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
