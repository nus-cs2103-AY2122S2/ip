package narcibot;

/**
 * Class for the handling of parsing of input.
 */
public class Parser {
    /**
     * Parse the command into an array of strings.
     * @param command the string to parse
     * @return the parsed array of string
     */
    public String[] parse(String command) {
        String[] tokens = command.split(" ",2);
        if(tokens.length != 1) {
            String[] tokens1 = tokens[1].split(" /by | /at ", 2);
            if(tokens1.length == 1) {
                return tokens;
            } else {
                return new String[] {tokens[0], tokens1[0], tokens1[1]};
            }
        } else {
            return tokens;
        }
    }
}
