import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

class Storage {

    public static ArrayList<Task> tasks = new ArrayList<Task>();
    public static File file = new File("data/duke.txt");
    public void createFile() throws IOException {
        File dir = new File("data/");
        if (!dir.exists()) {
            dir.mkdir();
        }

        File savedTasks = file;
        if (!savedTasks.exists()) {
            savedTasks.createNewFile();
        }
    }
    public void writeTasks( ) throws IOException {
        File destination = file;
        StringBuilder textToAdd = new StringBuilder();

        FileWriter fw = new FileWriter(destination);
        for (Task curr : tasks) {
            if (curr instanceof Todo) {
                textToAdd.append("T ")
                        .append(curr.isDone() ? "1 " : "0 ")
                        .append(curr.getName())
                        .append(System.lineSeparator());
            } else if (curr instanceof Deadline) {
                textToAdd.append("D ")
                        .append(curr.isDone() ? "1 " : "0 ")
                        .append(curr.getName())
                        .append(System.lineSeparator())
                        .append(((Deadline) curr).getBy())
                        .append(System.lineSeparator());
            } else if (curr instanceof Event) {
                textToAdd.append("E ")
                        .append(curr.isDone() ? "1 " : "0 ")
                        .append(curr.getName())
                        .append(System.lineSeparator())
                        .append(((Event) curr).getAt())
                        .append(System.lineSeparator());
            }
        }
        fw.write(textToAdd.toString());
        fw.close();
    }



    public ArrayList<Task> readTasks( ) throws FileNotFoundException {

        File source = file;
        Scanner sc = new Scanner(source);
        while (sc.hasNext()) {
            String current = sc.nextLine();
            String[] splits = current.split(" ", 3);
            switch (splits[0]) {
                case "T": {
                    Task toAdd = new Todo(splits[2], (splits[1].equals("1")));
                    tasks.add(toAdd);
                    break;
                }
                case "E": {
                    Task toAdd = new Event(splits[2], LocalDate.parse(sc.nextLine()), splits[1].equals("1"));
                    tasks.add(toAdd);
                    break;
                }
                case "D": {
                    Task toAdd = new Deadline(splits[2], LocalDate.parse(sc.nextLine()), splits[1].equals("1"));
                    tasks.add(toAdd);
                    break;
                }
                default:
                    System.out.println("Problem encountered when reading file: task unknown");
            }
        }
        return  tasks;
    }

}