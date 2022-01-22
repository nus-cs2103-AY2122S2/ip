import java.io.*;
import java.util.Scanner;

public class Storage {
    protected static String pwd;
    protected static String path;

    public Storage(String pwd, String path){
        Storage.pwd = pwd;
        Storage.path = path;
    }

    public static void storeToList(Task t) { //same as addToList but no printing
        TaskList.taskList.add(t);
        TaskList.numOfTask++;
    }

    public static void writeToFile(String path) throws IOException {
        FileWriter fw = new FileWriter(path);
        for(int i = 0; i < TaskList.numOfTask; i++) {
            Task t = TaskList.taskList.get(i);
            fw.write(craftOutput(t));
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    public static void updateTextFile() {
        try {
            writeToFile(Storage.pwd + Storage.path);
        } catch (IOException e) {
            System.out.println("Something happened to the text file !" + e.getMessage());
        }
    }

    public static void readFileDataAndStoreInList(File f) throws FileNotFoundException {
        Scanner sc = new Scanner(f);
        while ((sc.hasNextLine())) {
            String input = sc.nextLine();
            String[] inputSplit = input.split("\\|"); //split input by |
            String task = inputSplit[0];
            Integer mark = Integer.parseInt(inputSplit[1]);
            if(task.equals("T")) {
                Todo tempTask = new Todo(inputSplit[2]);
                if(mark == 1) {
                    tempTask.setTaskDone();
                }
                storeToList(tempTask);
            } else if(task.equals("D")) {
                Deadline tempTask = new Deadline(inputSplit[2],inputSplit[3]);
                if(mark == 1) {
                    tempTask.setTaskDone();
                }
                storeToList(tempTask);
            } else if (task.equals("E")) {
                Event tempTask = new Event(inputSplit[2],inputSplit[3]);
                if(mark == 1) {
                    tempTask.setTaskDone();
                }
                storeToList(tempTask);
            }
        }
    }

    public static String craftOutput(Task t) {
        String output = "";
        String doneIcon = t.getStatusIcon();
        if(t instanceof Todo) {
            if(doneIcon.equals("X")) {
                output = "T|1|" + t.getDescription();
            } else {
                output = "T|0|" + t.getDescription();
            }
        } else if(t instanceof Deadline) {
            if(doneIcon.equals("X")) {
                output = "D|1|" + t.getDescription() + "|" + ((Deadline) t).getBy();
            } else {
                output = "D|0|" + t.getDescription() + "|" + ((Deadline) t).getBy();
            }
        } else if(t instanceof Event) {
            if(doneIcon.equals("X")) {
                output = "E|1|" + t.getDescription() + "|" + ((Event) t).getAt();
            } else {
                output = "E|0|" + t.getDescription() + "|" + ((Event) t).getAt();
            }
        }
        return output;
    }

    public void load() throws IOException {
        File directory = new File(pwd + "/data");
        File inputFile = new File(pwd + path);
        if(directory.mkdir()) {
            System.out.println("You do not have the Directory, do not worry! I will create the directory for you");
        }
        if(inputFile.createNewFile()) {
            System.out.println("You do not have the file, do not worry! I will create the file for you");
        }
        readFileDataAndStoreInList(inputFile);
    }
}
