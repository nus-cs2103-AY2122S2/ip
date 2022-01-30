package li.zhongfu.cs2103.chatbot.types.ui;

import java.util.Map;

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

    public String getCmd() {
        return this.cmd;
    }

    public boolean hasArg(String key) {
        return this.args.containsKey(key);
    }

    public boolean hasNonblankArg(String key) {
        return this.hasArg(key) && !this.getArg(key).isBlank();
    }

    public String getArg(String key) {
        return this.args.get(key);
    }

    public boolean hasPosArg() {
        return this.hasArg("");
    }

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
    
    public Map<String, String> getArgs() {
        return this.args;
    }
    
}
