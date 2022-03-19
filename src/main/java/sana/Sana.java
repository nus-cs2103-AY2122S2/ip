package sana;

import sana.command.AddTask;
import sana.command.ByeCommand;
import sana.command.DeleteCommand;
import sana.command.FindCommand;
import sana.command.ListCommand;
import sana.command.MarkCommand;
import sana.command.UnmarkCommand;
import sana.command.UpdateCommand;
import sana.exception.SanaException;
import sana.exception.UnknownCommandException;
import sana.gui.Main;


/**
 * Sana is a BIG program!
 */
public class Sana {

    /** userTasks stores the commands given to Sana from the user */
    private TaskList userTasks;

    /** taskMem stores the tasks given to Sana in a txt file */
    private Memory taskMem;

    /** parser parses the user command */
    private Parser parser;

    /** generates responses from sana */
    private SanaResponse sanaResponse;

    /** main stage for sana GUI */
    private Main mainStage;

    /**
     * Constructor for the Sana class
     */
    public Sana() {
        this.parser = new Parser();
        this.sanaResponse = new SanaResponse();
        this.taskMem = new Memory();
        this.userTasks = new TaskList(taskMem.memToList());
    }

    /**
     * Sets the main stage
     *
     * @param stage stage to be set
     */
    public void setMainStage(Main stage) {
        this.mainStage = stage;
    }

    /**
     * This method controls the flow of logic of Sana depending on the user command
     *
     * @param userCommand   the user command
     */
    public String doCommand(String userCommand) {
        try {
            String[] parsedCmd = parser.parseCommand(userCommand);
            if (parsedCmd.length == 0) {
                return new UnknownCommandException().getMessage();
            }

            String command = parsedCmd[0];
            switch (command) {
            case "bye":
                return new ByeCommand().executeCommand(parsedCmd, userTasks);
            case "list":
                return new ListCommand().executeCommand(parsedCmd, userTasks);
            case "mark":
                return new MarkCommand().executeCommand(parsedCmd, userTasks);
            case "unmark":
                return new UnmarkCommand().executeCommand(parsedCmd, userTasks);
            case "todo":
            case "event":
            case "deadline":
                return new AddTask().executeCommand(parsedCmd, userTasks);
            case "delete":
                return new DeleteCommand().executeCommand(parsedCmd, userTasks);
            case "find":
                return new FindCommand().executeCommand(parsedCmd, userTasks);
            case "update":
                return new UpdateCommand().executeCommand(parsedCmd, userTasks);
            default:
                throw new UnknownCommandException();
            }
        } catch (SanaException e) {
            return e.getMessage();
        }
    }

    // GUI code portion

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        assert input != null;
        return doCommand(input);
    }
}
