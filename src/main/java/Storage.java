//@@author SimJM-reused
//Reused from https://github.com/garethkoh/ip/blob/master/src/main/java/Storage.java
// with minor modifications

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Storage {

    private static final File FOLDER_PATH = new File("../../../data");
    private static final File DATA_PATH = new File("../../../data/data.txt");

    public Storage(){}

    @SuppressWarnings("Unchecked")
    public static void readFile() {
        ArrayList<Task> toDoList = new ArrayList<>();
        try {
            if (FOLDER_PATH.mkdir()) {
                System.out.println("Folder is created!");
            } else {
                System.out.println("Folder already exists.");
            }

            if (DATA_PATH.createNewFile()) {
                System.out.println("File is created!");
            } else {
                System.out.println("File already exists.");
                FileInputStream reader = new FileInputStream(DATA_PATH);
                ObjectInputStream listInput = new ObjectInputStream(reader);
                try {
                    toDoList = (ArrayList<Task>) listInput.readObject();
                    for (int i = 0; i < toDoList.size(); i++) {
                        TaskBank.getBank().add(toDoList.get(i));
                    }
                    System.out.println("file read and data transferred");
                    listInput.close();
                } catch (ClassNotFoundException e) {
                    System.out.println("class not found");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    @SuppressWarnings("Unchecked")
//    public static ArrayList<Task> load() {
//        ArrayList<Task> toDoList = new ArrayList<>();
//        try {
//
//            if (FOLDER_PATH.mkdir()) {
//                Ui.print("Folder is created!\n");
//            } else {
//                Ui.print("Folder already exists.\n");
//            }
//            if (DATA_PATH.createNewFile()) {
//                Ui.print("File is created!\n");
//            } else {
//                Ui.print("File already exists.\n");
//                FileInputStream reader = new FileInputStream(DATA_PATH);
//                ObjectInputStream listInput = new ObjectInputStream(reader);
//                try {
//                    toDoList = (ArrayList<Task>) listInput.readObject();
//                    listInput.close();
//                } catch (ClassNotFoundException e) {
//                    Ui.print("class not found\n");
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return toDoList;
//    }

    public static void writeToFile() {
        try {
//            FileWriter fw = new FileWriter("data.txt",false);
            FileOutputStream writer = new FileOutputStream(DATA_PATH);
            ObjectOutputStream saveList = new ObjectOutputStream(writer);
            saveList.writeObject(TaskBank.getBank());
            saveList.close();
            System.out.println("saved successfully!");
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        } catch (IOException e) {
            System.out.println("unable to write file");
        }
    }
//    public static void save() {
//        try {
//            FileOutputStream writer = new FileOutputStream(DATA_PATH);
//            ObjectOutputStream saveList = new ObjectOutputStream(writer);
//            saveList.writeObject(TaskList.getToDoList());
//            saveList.close();
//            Ui.print("your list has been saved!\n");
//        } catch (FileNotFoundException e) {
//            Ui.print("file not found");
//        } catch (IOException e) {
//            Ui.print("failed to write to file");
//        }
//    }
}

//@@author