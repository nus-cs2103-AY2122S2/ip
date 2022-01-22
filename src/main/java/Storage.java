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
        String description = task.toString().substring(7);
        int mark = (task.getStatusIcon().equals("X") ? IS_MARKED : IS_UNMARKED);
        if (task instanceof ToDo) {
            finalOutput = "T|" + mark + "|" + description;
        } else if (task instanceof Deadline) {
            String[] str = description.split(" \\(by: ");
            String deadLineTask = str[0];
            String dateTime = str[1].substring(0, str[1].length() - 1);
            finalOutput = "D|" + mark + "|" + deadLineTask + "|" + dateTime;
        } else if (task instanceof Event) {
            String[] str = description.split(" \\(at: ");
            String eventTask = str[0];
            String dateTime = str[1].substring(0, str[1].length() - 1);
            finalOutput = "E|" + mark + "|" + eventTask + "|" + dateTime;
        }
        return finalOutput;
    }

    public void load() throws IOException {
        // Create a new directory from current working directory
        File directory = new File(currWorkingDirectory + "/data");

        // Create a new txt in filepath
        File txtFile = new File(currWorkingDirectory + filePath);

        if (directory.mkdir()) {
            System.out.println("Hmm kinda sussy you don't have the directory... lemme help you with that.");
        }

        if (txtFile.createNewFile()) {
            System.out.println("Hmm kinda sussy you don't have the txt file... lemme help you with that.");
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
                Task deadline = new Deadline(lineArr[2], lineArr[3]);
                if (isMarked(checkMarked)) {
                    deadline.mark();
                } else {
                    deadline.unmark();
                }
                TaskList.addToListNoPrint(deadline);
                break;
            case "E":
                Task event = new Event(lineArr[2], lineArr[3]);
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
