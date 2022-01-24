import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputOutputFunctions {

    final static String filePath = "data/ekud.txt";

    public static void printFileContents() throws FileNotFoundException {
        File f = new File(InputOutputFunctions.filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
    }

    public static void createNewFolderAndTextFile() throws IOException {
        File file = new File("data");
        file.mkdir();
        File file2 = new File(file, "ekud.txt");
        file2.createNewFile();
    }

    public static void appendToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(InputOutputFunctions.filePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    public static void setInFile(int lineNumber, String data) throws IOException {
        Path path = Paths.get(filePath);
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        for (String string : lines) {
            System.out.println(string);
        }
        lines.set(lineNumber - 1, data);
        Files.write(path, lines, StandardCharsets.UTF_8);
    }

    public static void writeToFile(List<Task> taskList) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : taskList) {
            stringBuilder.append(task.taskDescriptionForFile() + System.lineSeparator());
        }
        FileWriter fw = new FileWriter(InputOutputFunctions.filePath);
        fw.write(stringBuilder.toString());
        fw.close();
    }

    public static List<Task> loadFileContents() throws FileNotFoundException, IOException {
        ArrayList<Task> taskList = new ArrayList<>();
        Path path = Paths.get(filePath);
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        for (String l : lines) {
            String[] splicedS = l.split(" , ");
            switch (splicedS[0]) {
            case "T":
                ToDo toDo = new ToDo(splicedS[2]);
                if (splicedS[1].equals("1")) {
                    toDo.mark();
                }
                taskList.add(toDo);
                break;
            case "D":
                Deadline deadline = new Deadline(splicedS[2], splicedS[3]);
                if (splicedS[1].equals("1")) {
                    deadline.mark();
                }
                taskList.add(deadline);
                break;
            case "E":
                Event event = new Event(splicedS[2], splicedS[3]);
                if (splicedS[1].equals("1")) {
                    event.mark();
                }
                taskList.add(event);
                break;
            default:
            }
        }
        return taskList;
    }

}
