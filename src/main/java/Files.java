import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Files {
    public static void CreateFile() {
        try {
            File myObj = new File("data/duke.txt");
            if (myObj.createNewFile()) {
                System.out.println("Files created\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveTasks(List<Task> list) {
        try {
            FileWriter myWriter = new FileWriter("data/duke.txt");
            for (Task task : list) {
                String completed = task.isDone ? "1|" : "0|";
                if (task instanceof Deadline) {
                    myWriter.write("D|" + completed + task.description + "|" + ((Deadline) task).dateTime + "\n");
                } else if (task instanceof Event) {
                    myWriter.write("E|" + completed + task.description + "|" + ((Event) task).dateTime + "\n");
                } else {
                    myWriter.write("T|" + completed + task.description + "\n");
                }
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Task> readTasks(List<Task> list) {
        try {
            File myObj = new File("data/duke.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] tempList = data.split("\\|");

                Task holder;
                switch (tempList[0]) {
                    case "T":
                        holder = new Todo(tempList[2]);
                        break;
                    case "D":
                        holder = new Deadline(tempList[2], tempList[3]);
                        break;
                    default:
                        holder = new Event(tempList[2], tempList[3]);
                        break;
                }
                if (tempList[1].equals("1")) {
                    holder.markAsDone();
                }
                list.add(holder);
            }
            myReader.close();
            System.out.println("Files Loaded Successfully\n");
        } catch (FileNotFoundException e) {
            CreateFile();
        }
        return list;
    }
}
