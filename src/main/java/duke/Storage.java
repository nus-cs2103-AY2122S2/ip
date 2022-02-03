package duke;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String filePath;
    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    public Storage(String filePath){
        this.filePath = filePath;
    }

    /**
     * method to load tasks from file
     * @return a List<Task> which has tasks loaded from the file
     */
    public List<Task> load() {
        List<Task> tempList = new ArrayList<>();
        File file = new File(filePath);
        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String[] token = fileScanner.nextLine().split("\\|");
                if (token.length <= 2) {
                    throw new Exception_handler("Invalid line");
                }
                if(token[0].equals("T")){
                    tempList.add(new ToDos(token[2], token[1]));
                } else if(token[0].equals("D")) {
                    tempList.add(new Deadline(token[2], token[1], LocalDateTime.parse(token[3], dtf)));
                } else if(token[0].equals("E")) {
                    tempList.add(new Event(token[2], token[1], token[3]));
                } else {
                    throw new Exception_handler("Invalid task type");
                }
            }
            return tempList;
        }
        catch(Exception_handler de){
            System.out.println(de);
        }
        catch(IOException ie){
            System.out.println("IOException");
        }
        return tempList;
    }

    /**
     * method to write to file
     * @param tasks tasks to be written to the file
     * @throws IOException
     */
    public void writeToFile(List<Task> tasks) throws IOException {
        File file = new File(filePath);
        PrintWriter pw = new PrintWriter(file);
        String output = "";
        for (Task t : tasks) {
            if (t.getType().equals("T")) {
                output += t.getType() + "|" + t.getIsDone() + "|" + t.getDescription() + "\n";
            } else if (t.getType().equals("E")) {
                output += t.getType() + "|" + t.getIsDone() + "|" + t.getDescription() + "|"
                        + ((Event) t).getPlace() + "\n";
            } else if (t.getType().equals("D")) {
                output += t.getType() + "|" + t.getIsDone() + "|" + t.getDescription() + "|"
                        + ((Deadline) t).getDue() + "\n";
            }
        }
        pw.write(output);
        pw.close();
    }
}
