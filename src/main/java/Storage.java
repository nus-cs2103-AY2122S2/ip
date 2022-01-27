import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Storage {

    public String filePath;

    public Storage (String filePath) {
        this.filePath = filePath;
    }

    public void saveData() throws IOException {
        try {
            File file = new File("../../../data/duke.txt");
            if (!file.exists()) {
                file.createNewFile();
            }

            boolean createDirectory = file.getParentFile().mkdirs();
            boolean created = file.createNewFile();
            FileWriter fileWriter = new FileWriter(file, false);
            for (Task task : TaskList.dukeList) {
                String text = "";
                String description = task.description;
                //task.isDone = false;
                if (task instanceof ToDo) {
                    text = "T | " + (task.isDone ? "1" : "0") + " | " + description + '\n';
                } else if (task instanceof Event) {
                    Event event = ((Event) task);
                    text = "E | " + (task.isDone ? "1" : "0") + " | " + event.description.trim() + " | " + event.time + '\n';
                } else {
                    Deadline deadline = ((Deadline) task);
                    text = "D | " + (task.isDone ? "1" : "0") + " | " + deadline.description.trim() + " | " + deadline.time + '\n';
                }
                fileWriter.write(text);
                fileWriter.flush();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    public void loadData() throws IOException {
        File file = new File("../../../data/duke.txt");
        if(!file.exists()) {
            file.createNewFile();
        }

        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] commandWords = input.split(" \\| ", 4);
            Task task = null;
            switch (commandWords[0]) {
                case "D" -> {
                    task = new Deadline(commandWords[2], commandWords[3]);
                    display(input);
                }
                case "E" -> {
                    task = new Event(commandWords[2], commandWords[3]);
                    display(input);
                }
                case "T" -> {
                    task = new ToDo(commandWords[2]);
                    String text = "T | " + (task.isDone ? "1" : "0") + " | " + task.description + '\n';
                    display(input);
                }
                default -> System.err.println("error!!");
            }

            if (commandWords[1].equals("1")) {
                task.isComplete();
            }
            TaskList.dukeList.add(task);
        }
        sc.close();
    }

    public void display(String string) {
        System.out.println(string);
    }
}
