package dukeClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public String getStorageFilePath(){
        return filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        File file = new File(filePath);
        if (Files.notExists(Paths.get(filePath))) {
            try {
                file.createNewFile();
            } catch (IOException errorMessage) {
                new DukeException();
            }
        }
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException errorMessage) {
            new DukeException();
        }
        while (sc.hasNext()) {
            String data = sc.nextLine();
            String[] processedData = data.trim().split("]", 3);
            if (processedData[0].contains("T")) {
                ToDo todo = new ToDo(processedData[2].trim());
                if (processedData[1].contains("X")) {
                    todo.setIsDone(true);
                }
                tasks.add(todo);
            } else {
                String[] processedDataDescription = processedData[2].split("\\(by:", 2);
                String temp = processedDataDescription[1].replace(")", "");
                LocalDate date = null;
                try {
                    date = LocalDate.parse(temp.trim(), DateTimeFormatter.ofPattern("MMM dd yyyy"));
                } catch (DateTimeParseException errorMessage) {
                    new DukeException();
                    break;
                }
                if (processedData[0].contains("E")) {
                    Event event = new Event(processedDataDescription[0].trim(), date);
                    if (processedData[1].contains("X")) {
                        event.setIsDone(true);
                    }
                    tasks.add(event);
                } else if (processedData[0].contains("D")) {
                    Deadline deadline = new Deadline(processedDataDescription[0].trim(), date);
                    if (processedData[1].contains("X")) {
                        deadline.setIsDone(true);
                    }
                    tasks.add(deadline);
                } else {
                    System.out.println("Error in loading data");
                    new DukeException();
                }
            }
        }
        return tasks;
    }

    public void updateStorage(ArrayList<Task> dataArrList) throws DukeException{
        File file = new File(filePath);
        String updatedFileContents = "";
        for (int i = 0; i < dataArrList.size(); i++) {
            updatedFileContents = updatedFileContents.concat(
                String.format("    %s\n", dataArrList.get(i).identify()));
        }
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(updatedFileContents);
            fw.close();
        } catch (IOException errorMessage) {
            new DukeException();
        }
    }

    public void appendToStorage(Task task) throws DukeException {
        File file = new File(filePath);
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(String.format("    %s\n", task.identify()));
            fw.close();
        } catch (IOException errorMessage) {
            new DukeException();
        }
    }

}
