package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class to save and load data.
 */
public class Storage {

    /**
     * Method to write the task list to file.
     * @param list provides data to write tasks to the file.
     * @throws IOException
     */
    public void writeToFile(ArrayList<Task> list) throws IOException {
        try {
            File f = new File("/Users/riakhaitan/iP/ip/data/duke.txt");
            f.createNewFile();

            boolean directory = f.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(f, false);
            for (Task task : list) {
                String fileInput;
                Task ele = task;
                if (ele instanceof ToDo) {
                    fileInput = "[T][" + ele.getStatusIcon() + "]/" + ele.description;
                } else if (ele instanceof Deadline) {
                    Deadline deadL = (Deadline) ele;
                    fileInput = "[D][" + deadL.getStatusIcon() + "]/" + deadL.description + "/" + deadL.when;
                } else {
                    Event eve = (Event) ele;
                    fileInput = "[E][" + eve.getStatusIcon() + "]/" + eve.description + "/" + eve.when;
                }
                writer.write(fileInput + "\n");
                writer.flush();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Method to read tasks from the file.
     * @param list data is written to this variable.
     * @throws IOException
     */
    public void loadFromFile(ArrayList<Task> list) throws IOException {
        File f = new File("/Users/riakhaitan/iP/ip/data/duke.txt");
        f.createNewFile();
        String input, desc;
        Task t;
        Scanner in = new Scanner(f);
        while(in.hasNextLine()) {
            input = in.nextLine();
            String[] tSplit = Parser.splitForwardSlash(input);
            String[] splitT = Parser.splitBracket(tSplit[0]);
            switch(splitT[0]) {
            case "[T":
                t = new ToDo(tSplit[1]);
                break;
            case "[E":
                t = new Event(tSplit[1], LocalDate.parse(tSplit[2]));
                break;
            case "[D":
                t = new Deadline(tSplit[1], LocalDate.parse(tSplit[2]));
                break;
            default:
                t = new Task("eee");
                break;
            }
            if(splitT[1].equals("[X")) {
                t.done();
                list.add(t);
            }
            else {
                list.add(t);
            }
        }
        Ui.printList(list, list.size());
        in.close();
    }
}
