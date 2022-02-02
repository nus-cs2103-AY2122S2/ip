package chatbot.command;

/**
 * Output of commands.
 */
public class CommandOutput {
    public final String output;
    public final String sfxFile;
    public final boolean terminate;

    /**
     * Constructs a command with a specified output and sfx file.
     *
     * @param output  the command output
     * @param sfxFile the sfx file
     */
    public CommandOutput(String output, String sfxFile) {
        this.output = output;
        this.sfxFile = sfxFile;
        this.terminate = false;
    }

    /**
     * Constructs a command with a specified output and sfx file and terminate flag.
     *
     * @param output    the command output
     * @param sfxFile   the sfx file
     * @param terminate the terminate flag
     */
    public CommandOutput(String output, String sfxFile, boolean terminate) {
        this.output = output;
        this.sfxFile = sfxFile;
        this.terminate = terminate;
    }
}