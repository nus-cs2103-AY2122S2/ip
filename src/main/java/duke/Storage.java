package duke;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Storage {

    private static final File FOLDER_PATH = new File("../../../../data");
    private static final File DATA_PATH = new File("../../../../data/data.txt");

    public Storage(){
    }

    @SuppressWarnings("unchecked")
    public static <T> T castToAnything(Object obj) {
        return (T) obj;
    }

    public static ArrayList<Task> load() {
        ArrayList<Task> toDoList = new ArrayList<>();
        try {

            if (FOLDER_PATH.mkdir()) {
                Ui.print("Folder is created!\n");
            } else {
                Ui.print("Folder already exists.\n");
            }
            if (DATA_PATH.createNewFile()) {
                Ui.print("File is created!\n");
            } else {
                Ui.print("File already exists.\n");
                try {
                    FileInputStream reader = new FileInputStream(DATA_PATH);
                    ObjectInputStream listInput = new ObjectInputStream(reader);
                    toDoList = castToAnything(listInput.readObject());
                    listInput.close();
                    reader.close();
                } catch (ClassNotFoundException e) {
                    Ui.print("class not found\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return toDoList;
    }

    public static void save() {
        try {
            FileOutputStream writer = new FileOutputStream(DATA_PATH);
            ObjectOutputStream saveList = new ObjectOutputStream(writer);
            saveList.writeObject(TaskList.getToDoList());
            saveList.close();
            Ui.print("your list has been saved!\n");
        } catch (FileNotFoundException e) {
            Ui.print("file not found");
        } catch (IOException e) {
            Ui.print("failed to write to file");
        }
    }
}
