package istjbot.command;

import istjbot.exception.BotException;
import istjbot.storage.Storage;
import istjbot.text.TextList;
import istjbot.ui.Ui;

/**
 * Encapsulates the procedure of deleting a task or a note.
 */
public class DeleteCommand extends Command {

    public DeleteCommand(CommandEnum commandEnum, String fullCommand) {
        super(commandEnum, fullCommand);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TextList texts, Ui ui, Storage storage) throws BotException {
        String[] commandInfo = this.getFullCommand().split(" ");
        boolean shouldDeleteNote = deleteNote(commandInfo);
        int textNumber = extractTextNumber(commandInfo, texts, shouldDeleteNote);

        if (shouldDeleteNote) {
            String deleteNote = texts.deletedNoteString(textNumber);
            storage.save(texts);
            return ui.showNoteDeleted(texts.noteListSize(), deleteNote);
        } else {
            String deletedTask = texts.deletedTaskString(textNumber);
            storage.save(texts);
            return ui.showTaskDeleted(texts.taskListSize(), deletedTask);
        }
    }

    private boolean deleteNote(String[] commandInfo) throws BotException {
        if (commandInfo.length != 3) {
            throw new BotException("As an IstjBot, I can't delete a task or note in that command.");
        }

        String noteOrTask = commandInfo[1];
        if (noteOrTask.equals("note")) {
            return true;
        } else if (noteOrTask.equals("task")) {
            return false;
        } else {
            throw new BotException("As an IstjBot, I can only delete a note or a task.");
        }
    }

    private int extractTextNumber(String[] commandInfo, TextList tasks, boolean shouldDeleteNote)
            throws BotException {
        int textNumber;
        try {
            textNumber = Integer.parseInt(commandInfo[2]);
        } catch (NumberFormatException e) {
            throw new BotException("As an IstjBot, I don't think that is a proper index.");
        }

        // Error handle for improper index
        if (shouldDeleteNote) {
            if (textNumber < 1 || textNumber > tasks.noteListSize()) {
                throw new BotException("As an IstjBot, I don't think that is a proper index.");
            }
        } else {
            if (textNumber < 1 || textNumber > tasks.taskListSize()) {
                throw new BotException("As an IstjBot, I don't think that is a proper index.");
            }
        }

        return textNumber;
    }
}
