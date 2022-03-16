import java.util.Arrays;

public class Parser {

    private final String ADD_SUCCESS = "Got it! I've added:\n\t";


    private final String getDescription(String[] inputArr) {
        return inputArr[1].split("/")[0]; // split input from slash
    }

    private final DateTime getDateTime(String[] inputArr) {
        String[] dateTimeArr = inputArr[1].split("/")[1].split("[- ]"); // [String, yyyy, mm, dd, HHMM]
        return new DateTime(Arrays.copyOfRange(dateTimeArr, 1, dateTimeArr.length));
        // will reduce dateTimeArr to [yyyy, mm, dd, HHMM]
    }

    private void addToStorage(Task task, Storage storage) {
        storage.addTask(task);
    }

    public String parse(String input, Storage storage) throws DukeException {
        String[] inputArr = input.trim().split(" ", 2); // split first word from body
        switch(inputArr[0]) {
        case "todo":
            if (inputArr.length == 1) {
                throw new DukeException("todo simi?");
            }
            ToDos newToDo = new ToDos(getDescription(inputArr))
            addToStorage(newToDo, storage);
            return ADD_SUCCESS + newToDo;

        case "event":
            if (inputArr.length == 1) {
                throw new DukeException("event description?");
            }
            Events newEvent = new Events(getDescription(inputArr), getDateTime(inputArr));
            addToStorage(newEvent, storage);
            return ADD_SUCCESS + newEvent;

        case "deadline":
            if (inputArr.length == 1) {
                throw new DukeException("deadline when end?");
            }
            Deadlines newDeadline = new Deadlines(getDescription(inputArr), getDateTime(inputArr));
            return ADD_SUCCESS + newDeadline;

        default:
            throw new DukeException(":( OOPS!!!! I'm sorry, but I don't know what that means!");
    }
    }

    // parse input commands to create new To dos, Events or Deadlines
    public Task parse(String commandType, String[] inputArr) throws DukeException {
        switch (commandType) {
        case "todo":
            if (inputArr.length == 1) {
                throw new DukeException("todo simi?");
            }
            return new ToDos(getDescription(inputArr));

        case "event":
            if (inputArr.length == 1) {
                throw new DukeException("event description?");
            }
            return new Events(getDescription(inputArr), getDateTime(inputArr));

        case "deadline":
            if (inputArr.length == 1) {
                throw new DukeException("deadline when end?");
            }
            return new Deadlines(getDescription(inputArr), getDateTime(inputArr));

        default:
            throw new DukeException(":( OOPS!!!! I'm sorry, but I don't know what that means!");
        }

    }

    public String parse(String commandType, Storage storage, String[] inputArr) {
        switch(commandType) {
        case "bye":
            return "Sayonara~";


        }

    }
}
