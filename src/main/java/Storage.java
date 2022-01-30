import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    public ArrayList<Task> loadFile() throws FileNotFoundException {
        ArrayList<Task> taskArray = new ArrayList<Task>();
        try {
            File file = new File("bobby.txt");
            if (file.exists()) {
                Ui.fileExists();
                Scanner s = new Scanner(file);
                while (s.hasNext()) {
                    String line = s.nextLine();
                    String[] inputs = line.split(" ; ");
                    switch (inputs[0]) {
                    case "T":
                        Todo newToDo = new Todo(inputs[2]);
                        if (inputs[1].equals("true")) {
                            newToDo.markAsDone();
                        }
                        taskArray.add(newToDo);
                        break;
                    case "D":
                        Deadline newDeadline = new Deadline(inputs[2], LocalDate.parse(inputs[3]));
                        if (inputs[1].equals("true")) {
                            newDeadline.markAsDone();
                        }
                        taskArray.add(newDeadline);
                        break;
                    case "E":
                        Event newEvent = new Event(inputs[2], inputs[3]);
                        if (inputs[1].equals("true")) {
                            newEvent.markAsDone();
                        }
                        taskArray.add(newEvent);
                        break;
                    }
                }
            } else {
                file.createNewFile();
                Ui.newFileCreated();
            }
        } catch (IOException e) {
            System.out.println("An error has occurred.");
        }
        return taskArray;
    }

    public void updateFile(ArrayList<Task> taskArray) throws IOException {
        FileWriter fw = new FileWriter("bobby.txt");
        for (int i = 0; i < taskArray.size(); i++) {
            Task t = taskArray.get(i);
            t.writeToFile(fw);
        }
        fw.close();
    }
}
