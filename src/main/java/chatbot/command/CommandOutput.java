package chatbot.command;

/**
 * Output of commands.
 */
public class CommandOutput {
    public final String output;
    public final String sfxFile;

    /**
     * Constructs a command with a specified output and sfx file.
     * @param output the command output
     * @param sfxFile the sfx file
     */
    public CommandOutput(String output, String sfxFile) {
        this.output = output;
        this.sfxFile = sfxFile;
    }
}