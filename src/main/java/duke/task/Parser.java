package duke.task;

public class Parser {
    public Parser() {
    }

    public String[] parseDeadline(String string) throws IncompleteArgumentException {
        String[] result = string.split("/by");

        for (int i = 0; i < result.length; i++) {
            result[i] = result[i].trim();
        }

        if (result.length < 2) {
            throw new IncompleteArgumentException("Incomplete Argument");
        }
        return result;
    }

    public String[] parseEvent(String string) throws IncompleteArgumentException {
        String[] result = string.split("/at");

        for (int i = 0; i < result.length; i++) {
            result[i] = result[i].trim();
        }

        if (result.length < 2) {
            throw new IncompleteArgumentException("Incomplete Argument");
        }
        return result;
    }
}
