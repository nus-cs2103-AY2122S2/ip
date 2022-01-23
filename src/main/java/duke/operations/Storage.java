package duke.operations;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    protected static String filePath;
    protected static String currWorkingDirectory;
    private static final int IS_UNMARKED = 0;
    private static final int IS_MARKED = 1;

    public Storage(String filePath, String currWorkingDirectory) {
        Storage.filePath = filePath;
        Storage.currWorkingDirectory = currWorkingDirectory;
    }

    public static void updateTextFile() {
        try {
            writeToFile(currWorkingDirectory + filePath);
        } catch (IOException e) {
            System.out.println("Cannot write to txt file!");
        }
    }

    public static void writeToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task str : TaskList.taskArr) {
            fw.write(formatTextFileLine(str) + System.lineSeparator());
        }
        fw.close();
    }

    public static String formatTextFileLine(Task task) {
        String finalOutput= null;
        int mark = (task.getStatusIcon().equals("X") ? IS_MARKED : IS_UNMARKED);
        if (task instanceof ToDo) {
            finalOutput = "T|" + mark + "|" + task.getDescription();
        } else if (task instanceof Deadline) {
            finalOutput = "D|" + mark + "|" + task.getDescription()
                    + "|" + Parser.localDateToString(((Deadline) task).getDate())
                    + "|" + Parser.localTimeToString(((Deadline) task).getTime());
        } else if (task instanceof Event) {
            finalOutput = "E|" + mark + "|" + task.getDescription()
                    + "|" + Parser.localDateToString(((Event) task).getDate())
                    + "|" + Parser.localTimeToString(((Event) task).getStartTime())
                    + "|" + Parser.localTimeToString(((Event) task).getEndTime());
        }
        return finalOutput;
    }

    public void load() throws IOException {
        // Create a new directory from current working directory
        File directory = new File(currWorkingDirectory + "/DukeSaveDirectory");

        // Create a new txt in filepath
        File txtFile = new File(currWorkingDirectory + filePath);

        // returns true if directory is created
        if (directory.mkdir()) {
            System.out.println("Hmm kinda sussy you don't have the directory... it's aite, "
                    + "lemme help you with that.");
        }

        // returns true if file is created
        if (txtFile.createNewFile()) {
            System.out.println("Hmm kinda sussy you don't have the txt save file... it's aite, "
                    + "lemme help you with that.");
        }

        readFileContents(txtFile.toString());

    }

    public static void readFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNextLine()) {
            String currentLine = s.nextLine();
            String[] lineArr = currentLine.split("\\|");
            int checkMarked = Integer.parseInt(lineArr[1]);
            switch (lineArr[0]) {
            case "T":
                Task toDo = new ToDo(lineArr[2]);
                if (isMarked(checkMarked)) {
                    toDo.mark();
                } else {
                    toDo.unmark();
                }
                TaskList.addToListNoPrint(toDo);
                break;
            case "D":
                Task deadline = new Deadline(lineArr[2], Parser.stringToLocalDate(lineArr[3]),
                        Parser.stringToLocalTime(lineArr[4]));
                if (isMarked(checkMarked)) {
                    deadline.mark();
                } else {
                    deadline.unmark();
                }
                TaskList.addToListNoPrint(deadline);
                break;
            case "E":
                Task event = new Event(lineArr[2], Parser.stringToLocalDate(lineArr[3]),
                        Parser.stringToLocalTime(lineArr[4]), Parser.stringToLocalTime(lineArr[5]));
                if (isMarked(checkMarked)) {
                    event.mark();
                } else {
                    event.unmark();
                }
                TaskList.addToListNoPrint(event);
                break;
            default:
                break;
            }
        }
    }

    public static boolean isMarked(int markedNum) {
        return markedNum == 1;
    }
}
