package chatbot.util;

import chatbot.command.AddNonTodoTaskCommand;
import chatbot.command.ByeCommand;
import chatbot.command.Command;
import chatbot.command.ContactCommand;
import chatbot.command.DeleteCommand;
import chatbot.command.FindCommand;
import chatbot.command.GetCommand;
import chatbot.command.GuideCommand;
import chatbot.command.InvalidCommand;
import chatbot.command.ListCommand;
import chatbot.command.MarkCommand;
import chatbot.command.TodoCommand;
import chatbot.list.ContactList;
import chatbot.list.TaskList;


/**
 * Represents an interpreter for user inputs.
 */
public class Parser {

    private final Ui ui;
    private final Storage tasksStorage;
    private final Storage contactsStorage;
    private final TaskList taskList;
    private final ContactList contactList;

    /**
     * Instantiates a new Parser.
     *
     * @param ui The ChatBot UI handler.
     * @param tasksStorage The handler for loading tasks from and saving tasks to the save file.
     * @param contactsStorage The handler for loading contacts from and saving contacts to the save file.
     * @param taskList The list of tasks that the user has.
     * @param contactList The list of contacts that the user has.
     */
    public Parser(Ui ui, Storage tasksStorage, Storage contactsStorage, TaskList taskList, ContactList contactList) {
        this.ui = ui;
        this.tasksStorage = tasksStorage;
        this.contactsStorage = contactsStorage;
        this.taskList = taskList;
        this.contactList = contactList;
    }

    /**
     * Parses the input given by the user and attempts to carry out the appropriate commands.
     *
     * @param rawInput The input given by the user.
     * @return The response from Innkeeper to be displayed on the GUI.
     */
    public String parse(String rawInput) {
        String[] input = rawInput.split(" ");
        Command cmd;
        switch (input[0]) {
        case "bye":
            cmd = new ByeCommand();
            break;
        case "guide":
            cmd = new GuideCommand();
            break;
        case "deadline":
        case "event":
            cmd = new AddNonTodoTaskCommand(rawInput);
            break;
        case "list":
            cmd = new ListCommand(input);
            break;
        case "get":
            cmd = new GetCommand(input);
            break;
        case "find":
            cmd = new FindCommand(input);
            break;
        case "mark":
        case "unmark":
            cmd = new MarkCommand(input);
            break;
        case "todo":
            cmd = new TodoCommand(input);
            break;
        case "contact":
            cmd = new ContactCommand(input);
            break;
        case "delete":
            cmd = new DeleteCommand(input);
            break;
        default:
            cmd = new InvalidCommand();
        }
        return cmd.execute(taskList, contactList, tasksStorage, contactsStorage, ui);
    }
}
