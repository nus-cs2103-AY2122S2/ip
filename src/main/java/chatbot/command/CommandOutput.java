package chatbot.command;

/**
 * Output of commands.
 */
public class CommandOutput {
    public final String output;
    public final String sfxFile;
    public final boolean isTerminated;

    /**
     * Constructs a command with a specified output and sfx file.
     *
     * @param output  the command output
     * @param sfxFile the sfx file
     */
    public CommandOutput(String output, String sfxFile) {
        this.output = output;
        this.sfxFile = sfxFile;
        this.isTerminated = false;
    }

    /**
     * Constructs a command with a specified output and sfx file and terminate flag.
     *
     * @param output    the command output
     * @param sfxFile   the sfx file
     * @param isTerminated the terminate flag
     */
    public CommandOutput(String output, String sfxFile, boolean isTerminated) {
        this.output = output;
        this.sfxFile = sfxFile;
        this.isTerminated = isTerminated;
    }
}
