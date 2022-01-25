import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String path = Paths.get("").toAbsolutePath() + "/data/";
    private final File directory = new File(path);
    private static final File data = new File(path + "duke.txt");

    public void makeFile() {
        if (!directory.exists()) {
            try {
                directory.mkdir();
            } catch (Exception e) {
                System.out.println("OOPS!!! Directory cannot be created D:");
            }
        } else if (!data.exists()) {
            try {
                data.createNewFile();
            } catch (IOException e) {
                System.out.println("OOPS!!! File cannot be created D:");
            }
        }
    }

    public void loadFile(TaskList taskList) {
        try {
            FileInputStream fileInputStream = new FileInputStream(data);
            Scanner scanner = new Scanner(fileInputStream);
            while (scanner.hasNext()) {
                String input = scanner.nextLine();
                UserInput userInput = Parser.parseTask(input);
                taskList.addTaskWithoutMessage(userInput);
            }
        } catch (FileNotFoundException e) {
            System.out.println("OOPS!!! File not found D:");
        } catch (DukeException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeToFile(ArrayList<Task> list) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(data, false);
        for (Task task : list) {
            String toWrite = task.toString() + '\n';
            fileOutputStream.write(toWrite.getBytes(StandardCharsets.UTF_8));
        }
    }
}
