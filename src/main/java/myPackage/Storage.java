package myPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class Storage {

    public static void checkFile() throws IOException {
        try {
            File f = new File("data/duke.txt");
            if (!f.createNewFile()) {
                System.out.println("file created");
                ;
            } else {
                System.out.println("save file found");
            }
        } catch (IOException e) {
            System.out.println("file not found");
        }
    }

    public static void load(String fileLocation) throws FileNotFoundException {
        File f = new File(fileLocation);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String str = s.nextLine();
            if (str.length() > 0) {
                String[] stringArray = str.split("\\|");
                switch (stringArray[0]) {
                    case ("T") : {
                        TaskList.list.add(new ToDos(stringArray[2]));
                        break;
                    }
                    case ("D") : {
                        TaskList.list.add(new Deadlines(stringArray[2], stringArray[3]));
                        break;
                    }
                    case ("E") : {
                        TaskList.list.add(new Events(stringArray[2], stringArray[3]));
                        break;
                    }
                    default: {
                        System.out.println("wait your input is wrong");
                    }
                }
            }

        }
        TaskList.listCount = TaskList.list.size();
    }

    public static void save() {
        try {
            Path filePath = Paths.get("data/duke.txt");
            String s = "";
            for (int i = 0; i < TaskList.list.size(); i++) {
                s += TaskList.list.get(i).getTaskType() + "|" + TaskList.list.get(i).getStatusIcon() + "|"
                        + TaskList.list.get(i).getOriginalDescription() + "|" + TaskList.list.get(i).getTiming()
                        + System.lineSeparator();
            }
            writeToFile("data/duke.txt", s);
        } catch (IOException e) {
            File f = new File("data/duke.txt");
        }
    }

    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

}
