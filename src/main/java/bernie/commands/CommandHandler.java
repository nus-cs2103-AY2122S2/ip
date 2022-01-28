package bernie.commands;

import bernie.enums.Type;
import bernie.exceptions.BernieException;
import bernie.parser.Parser;
import bernie.storage.Storage;
import bernie.tasks.TaskList;
import bernie.ui.UiHandler;

import java.util.Scanner;

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
     * Displays to the user a message according to their input. Actions are performed according
     * to the input. Exceptions are caught and printed out for the user.
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
