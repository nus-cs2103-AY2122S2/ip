package duke;

public class Parser {
    private String input;

    Parser(String input) {
        this.input = input;
    }

    public ParsedAnswer parse() {
        String[] parsedString = input.toLowerCase().split(" ", 2);
        switch (parsedString[0]) {
            case "bye":
                return new ParsedAnswer("bye", -1);

            case "list":
                return new ParsedAnswer("list", -1);

            case "unmark":
                try {
                    int idx = Integer.parseInt(parsedString[1]);
                    return new ParsedAnswer("unmark", idx);
                } catch (Exception e) {
                    return new ParsedAnswer("unmark", -1);
                }

            case "mark" :
                try {
                    int idx = Integer.parseInt(parsedString[1]);
                    return new ParsedAnswer("mark", idx);
                } catch (Exception e) {
                    return new ParsedAnswer("mark", -1);
                }

            case "delete":
                try {
                    int idx = Integer.parseInt(parsedString[1]);
                    return new ParsedAnswer("delete", idx);
                } catch (Exception e) {
                    return new ParsedAnswer("delete", -1);
                }

            case "todo":
                try {
                    String desc = parsedString[1];
                    ParsedAnswer pa = new ParsedAnswer("todo", -1);
                    pa.setDesc(parsedString[1]);
                    return pa;
                } catch (Exception e) {
                    ParsedAnswer pa = new ParsedAnswer("error", -1);
                    pa.setDesc("The description cannot be empty!");
                    return pa;
                }

            case "event":
                try {
                    String [] eventParseBy = parsedString[1].split("/at");
                    ParsedAnswer pa;
                    if (eventParseBy.length <= 1) {
                        pa = new ParsedAnswer("error", -1);
                        pa.setDesc("Please check that your input format is correct.");
                    } else {
                        pa = new ParsedAnswer("event", -1);
                        pa.setDesc(eventParseBy[0]);
                        pa.setDate(eventParseBy[1]);
                    }
                    return pa;
                } catch (Exception e) {
                    ParsedAnswer pa = new ParsedAnswer("error", -1);
                    pa.setDesc("The description cannot be empty!");
                    return pa;
                }

            case "deadline":
                try {
                    String [] dlParseBy = parsedString[1].split("/by");
                    ParsedAnswer pa;
                    if (dlParseBy.length <= 1) {
                        pa = new ParsedAnswer("error", -1);
                        pa.setDesc("Please check that your input format is correct.");
                    } else {
                        pa = new ParsedAnswer("deadline", -1);
                        pa.setDesc(dlParseBy[0]);
                        pa.setDate(dlParseBy[1]);
                    }
                    return pa;
                } catch (Exception e) {
                    ParsedAnswer pa = new ParsedAnswer("error", -1);
                    pa.setDesc("The description cannot be empty!");
                    return pa;
                }

            case "find":
                try {
                    ParsedAnswer pa = new ParsedAnswer("find", -1);
                    pa.setDesc(parsedString[1]);
                    return pa;
                } catch (Exception e) {
                    ParsedAnswer pa = new ParsedAnswer("error", -1);
                    pa.setDesc("Please specify what you're searching for.");
                    return pa;
                }

            default:
                ParsedAnswer pa = new ParsedAnswer("error", - 1);
                pa.setDesc("Sorry, but I have no idea what you mean.");
                return pa;
        }
    }
}
