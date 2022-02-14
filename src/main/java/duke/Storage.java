package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 Class to enable the restoring and saving of the task list of the chatbot using the
 directory of the duke.java file.
 */
public class Storage {

    /**
     * Restores the contents of file from local directory
     *
     * @param storeList contents from file to be saved into this arraylist
     * @throws IOException If path is not valid
     */
    public static void restoreList(ArrayList<Task> storeList) throws IOException {
        String newFilePath = getNewFilePath();
        try {
            Files.createFile(Paths.get(newFilePath));
        } catch (FileAlreadyExistsException e) {
            restoreFromFile(storeList);
        }
    }

    private static void restoreFromFile(ArrayList<Task> storeList) throws FileNotFoundException {
        File f = new File(getNewFilePath());
        try (Scanner s = new Scanner(f)) {
            while (s.hasNextLine()) {
                String typeOfTask = s.nextLine();
                if (typeOfTask.equals("e")) {
                    Event created = new Event(s.nextLine(), s.nextLine());
                    if (s.nextLine().equals("1")) {
                        created.setMark();
                    } else {
                        created.setUnmark();
                    }
                    storeList.add(created);
                    continue;
                } else if (typeOfTask.equals("d")) {
                    Deadline created = new Deadline(s.nextLine(), s.nextLine());
                    if (s.nextLine().equals("1")) {
                        created.setMark();
                    } else {
                        created.setUnmark();
                    }
                    storeList.add(created);
                    continue;
                } else if (typeOfTask.equals("t")) {
                    Todo created = new Todo(s.nextLine());
                    if (s.nextLine().equals("1")) {
                        created.setMark();
                    } else {
                        created.setUnmark();
                    }
                    storeList.add(created);
                    continue;
                }
            }
        } catch (FileNotFoundException e) {
            assert false;
        }
    }

    /**
     * Saves the contents of file in local directory
     *
     * @param arrlist arraylist contents to be saved locally
     * @throws IOException If path is not valid
     */
    public static void saveList(ArrayList<Task> arrlist) throws IOException {
        int sizeOfList = arrlist.size();
        String newFilePath = getNewFilePath();
        eraseList(newFilePath);
        FileWriter fw = new FileWriter(newFilePath, true);
        for (int i = 0; i < sizeOfList; i++) {
            Task t = arrlist.get(i);
            if (t.getType() == 'e') {
                Event e = (Event) t;
                fw.write("e");
                fw.write("\n");
                fw.write(t.description);
                fw.write("\n");
                fw.write(e.eventDateTime);
                fw.write("\n");
                fw.write(t.isDone ? "1" : "0");
                fw.write("\n");
            } else if (t.getType() == 'd') {
                Deadline d = (Deadline) t;
                fw.write("d");
                fw.write("\n");
                fw.write(t.description);
                fw.write("\n");
                fw.write(d.deadlineDateTime);
                fw.write("\n");
                fw.write(t.isDone ? "1" : "0");
                fw.write("\n");
            } else if (t.getType() == 't') {
                Todo td = (Todo) t;
                fw.write("t");
                fw.write("\n");
                fw.write(t.description);
                fw.write("\n");
                fw.write(t.isDone ? "1" : "0");
                fw.write("\n");
            } else {
                assert sizeOfList == 0 : sizeOfList;
            }
        }
        fw.close();
    }

    private static String getNewFilePath() {
        String currentDirectory = Paths.get("Duke.java").toAbsolutePath().getParent().toString();
        String newFilePath = currentDirectory + "/duke.txt";
        return newFilePath;
    }

    /**
     * Erases the contents of file in local directory
     *
     * @param path path of the file to be erased
     * @throws IOException If path is not valid
     */
    public static void eraseList(String path) throws IOException {
        PrintWriter writer = new PrintWriter(new File(path));
        writer.print("");
        writer.close();
    }
}
