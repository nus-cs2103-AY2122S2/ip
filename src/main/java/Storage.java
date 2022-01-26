import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Storage {
    private final String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException {
        // TODO: 26/1/2022
        ArrayList<Task> result = new ArrayList<>();
        try {
            File path = new File(filePath);
            File directory = new File("data");
            if (directory.mkdir()) {
                System.out.println("directory ./data/ created");
                File data = new File(filePath);
                data.createNewFile();
                System.out.println("then, file ./data/tasks is also created");
                return result;
            } else {
                if (path.createNewFile()) {
                    System.out.println("file ./data/tasks.txt created");
                } else {
                    System.out.println("normal");
                    result = loadFile(path);
                    return result;
                }
                return result;
            }


        } catch(IOException e) {
            System.out.println(e);
            return result;
        }

    }

    public ArrayList<Task> loadFile(File path) throws FileNotFoundException, IOException {
        FileReader reader = new FileReader(path);
        BufferedReader br = new BufferedReader(reader);
        ArrayList<Task> result = new ArrayList<>();
        while(br.ready()) {
            String[] task = br.readLine().split(" \\| ");
            boolean done;
            if (task[1].equals("1")) {
                done = true;
            } else {
                done = false;
            }
            switch (task[0]) {
            case "T":
                result.add(new Todo(task[2], done));
                break;
            case "D":
                result.add(new Deadline(task[2], task[3], done));
                break;
            case "E":
                result.add(new Event(task[2], task[3], done));
                break;
            }
        }
        return result;
    }

    public void saveFile(TaskList taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        for (int i = 0; i <taskList.size() ; i++) {
            fileWriter.write(taskList.get(i).dataFormatOfTask() + "\n");
        }
        fileWriter.close();
    }


}
