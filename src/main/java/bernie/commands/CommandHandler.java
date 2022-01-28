package bernie.commands;

import bernie.enums.Type;
import bernie.exceptions.BernieException;
import bernie.parser.Parser;
import bernie.storage.Storage;
import bernie.tasks.TaskList;
import bernie.ui.UiHandler;

import java.util.Scanner;

/**
 * CommandHandler determines which Command to execute given the user input. Parser helps to
 * parse the user input to help CommandHandler determine the respective Command.
 */
public class CommandHandler {
    private TaskList tasks;
    private UiHandler uiHandler;
    private Storage storage;
    private Parser parser;

    /**
     * Constructs the commandHandler to handle user inputs
     */
    public CommandHandler() {
        this.tasks = new TaskList();
        this.uiHandler = new UiHandler();
        this.storage = new Storage();
        this.parser = new Parser(tasks);
    }

    /**
     * Commands are performed according to the input, done by creating the respective commands and
     * executing it. Parser helps to determine the Type given the user input. uiHandler displays to
     * the user the relevant message according to their input.
     * Exceptions are caught and printed out for the user.
     * @param input String, user input
     * @return boolean value, indicating if the program will end or not.
     */
    public boolean handle (String input) {
        Type type = parser.parseType(input);
        try {
            switch (type) {
            case LIST:
                uiHandler.showListTasksMsg(tasks);
                break;
            case BYE:
                uiHandler.showLeaveMsg();
                break;
            case EMPTY:
                throw new BernieException("Say something???");
            case MARK:
                MarkCommand markCommand = new MarkCommand(tasks, uiHandler, storage, parser, input);
                markCommand.execute();
                break;
            case DELETE:
                DeleteCommand deleteCommand = new DeleteCommand(tasks, uiHandler, storage, parser, input);
                deleteCommand.execute();
                break;
            case ADD:
                AddCommand addCommand = new AddCommand(tasks, uiHandler, storage, parser, input);
                addCommand.execute();
                break;
            case FIND:
                FindCommand findCommand = new FindCommand(tasks, uiHandler, storage, parser, input);
                findCommand.execute();
            default:
                break;
            }
        } catch (BernieException e) {
            uiHandler.showErrorMsg(e.getMessage());
        } catch (Exception e) {
            uiHandler.showErrorMsg(e.getMessage());
        } finally {
            return input.equals("bye");
        }
    }

    /**
     * Runs the CommandHandler. uIhandler greets the user and storage loads tasks if the program was run
     * previously. CommandHandler continuously takes in the user input until the user ends the program.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        uiHandler.greet();
        storage.loadTasks();
        while (true) {
            String input = sc.nextLine();
            boolean end = handle(input);
            if (end) {
                break;
            }
        }
    }
}
