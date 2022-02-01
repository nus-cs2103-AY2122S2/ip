package bernie.commands;

import java.util.Scanner;

import bernie.enums.Type;
import bernie.exceptions.BernieException;
import bernie.parser.Parser;
import bernie.storage.Storage;
import bernie.tasks.TaskList;
import bernie.ui.InputResponder;

/**
 * CommandHandler determines which Command to execute given the user input. Parser helps to
 * parse the user input to help CommandHandler determine the respective Command.
 */
public class CommandHandler {
    private TaskList tasks;
    private InputResponder inputResponder;
    private Storage storage;
    private Parser parser;

    /**
     * Constructs the commandHandler to handle user inputs
     */
    public CommandHandler() {
        this.tasks = new TaskList();
        this.inputResponder = new InputResponder();
        this.storage = new Storage();
        this.parser = new Parser(tasks);
    }

    /**
     * Commands are performed according to the input, done by creating the respective commands and
     * executing it. Parser helps to determine the Type given the user input. inputResponder displays to
     * the user the relevant message according to their input.
     * Exceptions are caught and printed out for the user.
     * @param input String, user input
     * @return String, the resulting message
     */
    public String handle(String input) {
        Type type = parser.parseType(input);
        String executeOutput = null;
        try {
            switch (type) {
            case LIST:
                executeOutput = inputResponder.showListTasksMsg(tasks);
                break;
            case BYE:
                executeOutput = inputResponder.showLeaveMsg();
                break;
            case EMPTY:
                throw new BernieException("Say something???");
            case MARK:
                MarkCommand markCommand = new MarkCommand(tasks, inputResponder, storage, parser, input);
                executeOutput = markCommand.execute();
                break;
            case DELETE:
                DeleteCommand deleteCommand = new DeleteCommand(tasks, inputResponder, storage, parser, input);
                executeOutput = deleteCommand.execute();
                break;
            case ADD:
                AddCommand addCommand = new AddCommand(tasks, inputResponder, storage, parser, input);
                executeOutput = addCommand.execute();
                break;
            case FIND:
                FindCommand findCommand = new FindCommand(tasks, inputResponder, storage, parser, input);
                executeOutput = findCommand.execute();
                break;
            default:
                break;
            }
            return executeOutput;
        } catch (BernieException e) {
            return inputResponder.showErrorMsg(e.getMessage());
        } catch (Exception e) {
            return inputResponder.showErrorMsg(e.getMessage());
        }

    }
}
