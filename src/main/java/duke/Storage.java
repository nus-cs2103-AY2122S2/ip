package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Represents a storage that loads and save user data
 */
public class Storage {
    //@@author SimJM-reused
    //Reused from https://github.com/eugenechiaay/ip/blob/master/src/main/java/gene/component/TaskStorage.java
    // with minor modifications
    private String currentDir = System.getProperty("user.dir");
    private Path currentPath = Path.of(currentDir + File.separator + "data");
    private final File folderPath = new File(currentPath.toString());
    private final File dataPath = new File(currentPath.toString() + File.separator + "duke.txt");

    /**
     * Constructor for a Storage object
     */
    public Storage(){}

    /**
     * Loads the users task list from file
     */
    @SuppressWarnings("Unchecked")
    public ArrayList<Task> readFile() {
        ArrayList<Task> toDoList = new ArrayList<>();
        try {
            if (folderPath.mkdir()) {
                System.out.println("Folder is created!");
            } else {
                System.out.println("Folder already exists.");
            }

            if (dataPath.createNewFile()) {
                System.out.println("File is created!");
            } else {
                System.out.println("File already exists.");
                try {
                    FileReader fileReader = new FileReader(dataPath);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] tokens = line.split(" / ");
                        String taskDescription = tokens[1];
                        switch(tokens[0]) {
                        case "todo":
                            Todo todo = new Todo(taskDescription);
                            toDoList.add(todo);
                            break;
                        case "deadline":
                            String dateDeadline = tokens[2];
                            Deadline deadline = new Deadline(taskDescription, dateDeadline);
                            String isDone = tokens[3];
                            if (isDone.equals("X")) {
                                deadline.markAsDone();
                            }
                            toDoList.add(deadline);
                            break;
                        case "event":
                            String dateEvent = tokens[2];
                            Event event = new Event(taskDescription, dateEvent);
                            String isMarked = tokens[3];
                            if (isMarked.equals("X")) {
                                event.markAsDone();
                            }
                            toDoList.add(event);
                            break;
                        default:
                            break;
                        }
                    }
                    fileReader.close();
                    bufferedReader.close();
                    System.out.println("file read and data transferred");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return toDoList;
    }

    /**
     * Save the users tasklist to file
     */
    public void writeToFile(ArrayList<Task> bank) {
        try {
            FileWriter fileWriter = new FileWriter(dataPath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < bank.size(); i++) {
                Task t = bank.get(i);
                String taskSym = t.getSym();
                String toWrite = "";
                switch(taskSym) {
                case "T":
                    toWrite = "todo / " + t.getDescription() + " / " + t.getStatusIcon() + "\n";
                    bufferedWriter.append(toWrite);
                    break;
                case "D":
                    toWrite = "deadline / " + t.getDescription() + " / " + t.getDayString() + " / "
                            + t.getStatusIcon() + "\n";
                    bufferedWriter.append(toWrite);
                    break;
                case "E":
                    toWrite = "event / " + t.getDescription() + " / " + t.getDayString() + " / "
                            + t.getStatusIcon() + "\n";
                    bufferedWriter.append(toWrite);
                    break;
                default:
                    break;
                }
            }
            bufferedWriter.close();
            System.out.println("saved successfully!");
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        } catch (IOException e) {
            System.out.println("unable to write file");
        }
    }
    //@@author
}
