package duke;

/**
 * This class is a child of the Parser class. It parse with file inputs.
 * @author Sim Jun Heng
 * @version CS2103T AY21/22 Sem 2
 */
public class FileParser extends Parser {
    private boolean isDone;

    /**
     * Parses file input.
     *
     * @param str the string entered by the user.
     */
    public FileParser(String str) throws DukeException {
        String[] dataArray = str.split(",");
        switch(dataArray[0]) {
        case "T":
            if (dataArray[1].equals("1")) {
                isDone = true;
            } else {
                isDone = false;
            }
            command = "todo";
            desc = dataArray[2];
            break;
        case "D":
            if (dataArray[1].equals("1")) {
                isDone = true;
            } else {
                isDone = false;
            }
            command = "deadline";
            desc = dataArray[2];
            date = dataArray[3];
            break;
        case "E":
            if (dataArray[1].equals("1")) {
                isDone = true;
            } else {
                isDone = false;
            }
            command = "event";
            desc = dataArray[2];
            date = dataArray[3];
            break;
        default:
            throw new DukeException("Error in file. Please try again.");
        }

    }

    public boolean getIsDone() {
        return isDone;
    }
}
