package juke;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private void loadData(Scanner sc, ArrayList<Task> arrList) {
        while (sc.hasNext()) {
            Task t = Parser.stringToTask(sc.nextLine());
            arrList.add(t);
        }
    }

    public ArrayList<Task> load(){
        ArrayList<Task> arrList = new ArrayList<>(100);
        try {
            File f = new File("data/Juke.txt");
            checkFile(f);
            Scanner s = new Scanner(f);
            loadData(s, arrList);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return arrList;
    }

    private void checkFile(File f) throws IOException {
        File folder = new File("./data");
        boolean fileMissing = !f.exists();
        boolean folderMissing = !folder.exists();
        if (folderMissing) {
            boolean folderCreated = folder.mkdir();
            boolean fileCreated = f.createNewFile();
            System.out.println(folderCreated && fileCreated);
        } else if (fileMissing) {
            boolean isFileCreated = f.createNewFile();
            System.out.println(isFileCreated);
        }
    }

}
