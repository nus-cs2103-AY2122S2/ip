public class Parser {
    private String input;
    private ParsedAnswer answer;
    Parser(String input) {
        this.input = input;
    }

    public ParsedAnswer parse() {
        String[] parsedString = input.toLowerCase().split(" ", 2);
    }

}
