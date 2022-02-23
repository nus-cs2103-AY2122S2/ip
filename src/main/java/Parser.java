import java.util.Arrays;

public class Parser {

    private static final String getDescription(String[] inputArr) {
        return inputArr[1].split("/")[0]; // split input from slash
    }

    private static final DateTime getDateTime(String[] inputArr) {
        String[] dateTimeArr = inputArr[1].split("/")[1].split("[- ]"); // [String, yyyy, mm, dd, HHMM]
        return new DateTime(Arrays.copyOfRange(dateTimeArr, 1, dateTimeArr.length));
        // will reduce dateTimeArr to [yyyy, mm, dd, HHMM]


    }
    // parse input commands to create new To dos, Events or Deadlines
    public Task parse(String commandType, String[] inputArr) throws DukeException {
        switch (commandType) {
        case "todo":
            return new ToDos(getDescription(inputArr));

        case "event":
            return new Events(getDescription(inputArr), getDateTime(inputArr));

        case "deadline":
            new Deadlines(getDescription(inputArr), getDateTime(inputArr));

        default:
            throw new DukeException(":( OOPS!!!! I'm sorry, but I don't know what that means!")
        }

    }
}
