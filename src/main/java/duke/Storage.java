package duke;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String directory;
    private String filePath;

    public Storage(String directory, String filePath) {
        this.directory = directory;
        this.filePath = filePath;
    }

    public void reSavingFiles (ArrayList<Task> list_of_inputs) throws IOException {
        //start saving the results for next return
        for (int i = 0; i < list_of_inputs.size(); i++) {
            if (i == 0) {
                writeToFile(list_of_inputs.get(i).message(),filePath);
            } else {
                list_of_inputs.get(i).updateData(filePath);

            }
        }
    }


    public void readData(String filePath) {
        try {
            File f = new File(filePath);
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot read data!!!");
        }
    }

    private static void writeToFile(String textToAdd, String filePath) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(textToAdd);
        fileWriter.close();
    }


    public void updateData(String message,String filePath) throws IOException {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write("\n" + message);
            fw.close();

    }


}