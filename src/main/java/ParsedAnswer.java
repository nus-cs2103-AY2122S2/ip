public class ParsedAnswer {
    private String command;
    private Integer index;

    ParsedAnswer(String command, Integer index) {
        this.command = command;
        this.index = index;
    }

    public String getCommand() {
        return this.command;
    }

    public Integer getIndex() {
        return this.index;
    }
}
