package istjbot.command;

import java.time.format.DateTimeParseException;

import istjbot.exception.BotException;
import istjbot.storage.Storage;
import istjbot.text.TextList;
import istjbot.ui.Ui;

/**
 * Encapsulates the procedure of adding a task or a note.
 */
public class AddCommand extends Command {
    /**
     * Constructor for this class.
     *
     * @param commandEnum CommandEnum indicating a specific type of command.
     * @param fullCommand Full information required for execution.
     */
    public AddCommand(CommandEnum commandEnum, String fullCommand) {
        super(commandEnum, fullCommand);
    }

    /**
     * Returns whether this command is a terminal command.
     *
     * @return False as AddCommand is not a terminal command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the procedure of adding a specific type of task or a note.
     *
     * @param texts TextList responsible for adding a task or a note specified by the user.
     * @param ui Text part of the User Interface.
     * @param storage Storage responsible for saving the task or the note into an external file.
     * @throws BotException When the text cannot be added due to incomplete information provided.
     */
    @Override
    public String execute(TextList texts, Ui ui, Storage storage) throws BotException {
        CommandEnum noteOrTaskType = this.getCommandEnum();
        String[] textInfo = extractTextInfo(noteOrTaskType);

        // TextList
        try {
            if (noteOrTaskType == CommandEnum.NOTE) {
                texts.addNote(textInfo[0]);
            } else {
                texts.addTask(this.getCommandEnum(), textInfo[0], textInfo[1]);
            }
        } catch (DateTimeParseException e) {
            throw new BotException("As an IstjBot, I don't think that is a proper date you entered.");
        }

        // Storage
        storage.save(texts);

        // Ui
        if (noteOrTaskType == CommandEnum.NOTE) {
            return ui.showNoteAdded(texts.noteListSize(), texts.noteString(texts.noteListSize()));
        } else {
            return ui.showTaskAdded(texts.taskListSize(), texts.taskString(texts.taskListSize()));
        }
    }

    private String[] extractTextInfo(CommandEnum noteOrTaskType) throws BotException {
        String[] commandInfo = this.getFullCommand().split(" ");

        // Error handle for text with no description (and no modifier date)
        if (commandInfo.length == 1) {
            throw new BotException("As an IstjBot, I cannot find any description for your text.");
        }

        String description = "";
        int modifier = -1;
        boolean foundModifierDate = false;
        String modifierDate = "";

        // Extracting text description
        for (int i = 1; i < commandInfo.length; i++) {
            if (noteOrTaskType == CommandEnum.DEADLINE && commandInfo[i].equals("/by")) {
                modifier = i;
                foundModifierDate = true;
                break;
            } else if (noteOrTaskType == CommandEnum.EVENT && commandInfo[i].equals("/at")) {
                modifier = i;
                foundModifierDate = true;
                break;
            }
            if (i == 1) {
                description += commandInfo[i];
            } else {
                description += " " + commandInfo[i];
            }
        }

        // Error handle for no description but with modifier date indication,
        // or no modifier date indication at all for special tasks
        if (description.equals("")) {
            throw new BotException("As an IstjBot, I cannot add a special task with no description.");
        } else if (!foundModifierDate) {
            if (noteOrTaskType == CommandEnum.DEADLINE || noteOrTaskType == CommandEnum.EVENT) {
                throw new BotException("As an IstjBot, I cannot add a special task with no timing attached.");
            }
        }

        // Extracting modifier date for special tasks
        if (noteOrTaskType == CommandEnum.DEADLINE || noteOrTaskType == CommandEnum.EVENT) {
            for (int i = modifier + 1; i < commandInfo.length; i++) {
                modifierDate += i == commandInfo.length - 1 ? commandInfo[i] : commandInfo[i] + " ";
            }
        }

        // Error handle for no modifier date for special tasks
        if (modifierDate.equals("")) {
            if (noteOrTaskType == CommandEnum.DEADLINE || noteOrTaskType == CommandEnum.EVENT) {
                throw new BotException("As an IstjBot, I cannot add a special task with no timing attached.");
            }
        }

        return new String[]{description, modifierDate};
    }
}
