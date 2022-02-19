package duke;

import java.util.ArrayList;

/**
 * This class is a child of the Parser class. It parse with file inputs.
 * @author Sim Jun Heng
 * @version CS2103T AY21/22 Sem 2
 */
public class FileParser extends Parser {
    private boolean isDone;
    private ArrayList<String> tagArray = new ArrayList<>();

    /**
     * Parses file input.
     *
     * @param str the string received from the file.
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
            for (int i = 3; i < dataArray.length; i++) {
                tagArray.add(dataArray[i]);
            }
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
            for (int i = 4; i < dataArray.length; i++) {
                tagArray.add(dataArray[i]);
            }
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
            for (int i = 4; i < dataArray.length; i++) {
                tagArray.add(dataArray[i]);
            }
            break;
        default:
            throw new DukeException("Error in file. Please try again.");
        }

    }

    public boolean getIsDone() {
        return isDone;
    }

    public ArrayList<String> getTagArray() {
        return tagArray;
    }
}
