import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class DukeFile {

    private File file;

    public DukeFile() {
        file = new File("data.txt");

        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> readData() {
        ArrayList<Task> list = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String[] temp = scanner.nextLine().split("\\|");
                Task task;

                if (temp[0].equals("D")) {
                    task = new Deadline(temp[2], LocalDate.parse(temp[3]));
                } else if (temp[0].equals("E")) {
                    task = new Event(temp[2], LocalDate.parse(temp[3]));
                } else {
                    task = new Todo(temp[2]);
                }

                if (temp[1].equals("X")) {
                    task.markTask(true, false);
                }
                list.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("OOPS!!! Duke is unable to locate your file!");
        }
        return list;
    }

    public void saveData(ArrayList<Task> list) {
        try {
            FileWriter fileWriter = new FileWriter(file);

            for (Task task: list) {
                fileWriter.write(task.getCharId()
                        + "|"
                        + task.getMark()
                        + "|"
                        + task.getUserInput()
                        + "|"
                        + task.getDate()
                        + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("OOPS!!! Duke is unable to write to your file!");
        }
    }
}
