package li.zhongfu.cs2103.chatbot.types.ui;

import java.util.Map;

/**
 * A class used to encapsulate the result of parsing a user command.
 * 
 * Parsed commands consist of the command keyword, a optional positional arg, and optional named args.
 */
public class ParserResult {
    private String cmd;
    private Map<String, String> args;

    /**
     * Class used to encapsulate the result from Parser.
     *
     * @param cmd the parsed command
     * @param args the parsed arguments
     */
    public ParserResult(String cmd, Map<String, String> args) {
        this.cmd = cmd;
        this.args = args;
    }

    /**
     * Returns the command keyword.
     * 
     * @return the command keyword
     */
    public String getCmd() {
        return this.cmd;
    }

    /**
     * Returns a boolean indicating whether the named argument was in the parsed command.
     * 
     * @param key the named argument key to search for
     * @return true if the named argument exists, false otherwise
     */
    public boolean hasArg(String key) {
        return this.args.containsKey(key);
    }

    /**
     * Returns a boolean indicating whether the named argument was in the parsed command, and is non-blank.
     * 
     * @param key the named argument key to search for
     * @return true if the named argument exists and is non-blank, false otherwise
     */
    public boolean hasNonblankArg(String key) {
        return this.hasArg(key) && !this.getArg(key).isBlank();
    }

    /**
     * Returns the named argument value associated with the argument key.
     * 
     * @param key the named argument key to search for
     * @return the associated named argument value if it exists, or null otherwise
     */
    public String getArg(String key) {
        return this.args.get(key);
    }

    /**
     * Returns a boolean indicating whether a positional argument was parsed.
     * 
     * @return true if a positional argument was parsed, false otherwise
     */
    public boolean hasPosArg() {
        return this.hasArg("");
    }

    /**
     * Returns a boolean indicating whether a positional argument was parsed, and is non-blank.
     * 
     * @return true if a positional argument was parsed and is non-blank, false otherwise
     */
    public boolean hasNonblankPosArg() {
        return this.hasNonblankArg("");
    }

    /**
     * Returns the positional argument
     *
     * @return the positional argument
     */
    public String getPosArg() {
        return this.getArg("");
    }

    /**
     * Returns a {@code Map<String, String>} containing all parsed arguments.
     * 
     * @return a {@code Map<String, String>} containing all parsed arguments
     */
    public Map<String, String> getArgs() {
        return this.args;
    }

}
