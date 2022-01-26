import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Aeromon {
    private final ArrayList<Task> tasks;
    private Scanner sc;
    private File localTasks;

    private enum TaskType { TODO, DEADLINE, EVENT }

    public Aeromon() {
        this.tasks = new ArrayList<Task>();
    }

    public static void main(String[] args) {
        Aeromon aeromon = new Aeromon();
        aeromon.start();
    }

    private void start() {
        getFile();
        greet();

        sc = new Scanner(System.in);

        execute:
        while (sc.hasNextLine()) {
            try {
                String command = sc.next();

                switch (command) {
                    case "bye":
                        bye();
                        break execute;

                    case "list":
                        list();
                        break;

                    case "mark": {
                        int index = getTaskNum() - 1;
                        Task temp = tasks.get(index);
                        temp.markAsDone();
                        saveFile();
                        break;
                    }

                    case "unmark": {
                        int index = getTaskNum() - 1;
                        Task temp = tasks.get(index);
                        temp.markAsNotDone();
                        saveFile();
                        break;
                    }

                    case "todo": {
                        addTask(TaskType.TODO);
                        break;
                    }

                    case "deadline": {
                        addTask(TaskType.DEADLINE);
                        break;
                    }

                    case "event": {
                        addTask(TaskType.EVENT);
                        break;
                    }

                    case "delete": {
                        int index = getTaskNum() - 1;
                        remove(index);
                        saveFile();
                        break;
                    }

                    default:
                        throw new AeromonException("Nani? Me no understand what you say .-.");
                }
            } catch (AeromonException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void greet() {
        System.out.println("Hey, Aeromon here! Fancy a cup of tea?");
    }

    private void bye() {
        System.out.println("Buai Buai! Ciao for now!");
    }

    private void list() {
        System.out.println("Konnichiwassup! Look at how much work you have to do!");
        int count = 1;
        for (Task item : tasks) {
            System.out.println(String.format("%d: %s", count, item));
            count++;
        }
        System.out.println(getTasksStatus());
    }

    private void addTask(TaskType taskType) throws AeromonException {
        System.out.println("Nicely! I've added for you:");

        switch(taskType) {
            case TODO: {
                String description = getDescription("todo");
                ToDo task = new ToDo(description.trim());
                tasks.add(task);
                System.out.println(task);
                System.out.println(getTasksStatus());
                break;
            }
            case DEADLINE: {
                String description = getDescription("deadline");
                String[] info = description.split("/by ");
                Deadline task = new Deadline(info[0].trim(), info[1]);
                tasks.add(task);
                System.out.println(task);
                System.out.println(getTasksStatus());
                break;
            }
            case EVENT: {
                String description = getDescription("event");
                String[] info = description.split("/at ");
                Event task = new Event(info[0].trim(), info[1]);
                tasks.add(task);
                System.out.println(task);
                System.out.println(getTasksStatus());
                break;
            }
        }
        saveFile();
    }

    private void remove(int index) {
        Task temp = this.tasks.remove(index);
        System.out.println("Okies, one less thing on the list! \n" + temp);
        System.out.printf("Less burden for you since you only have %d tasks to do *smiles*%n", tasks.size());
    }

    private String getDescription(String taskType) throws AeromonException {
        String description = sc.nextLine().trim();
        if (description.isEmpty()) {
            throw new AeromonException(String.format("What do you want to do? The description of %s cannot be empty!", taskType));
        } else {
            return description;
        }
    }

    private int getTaskNum() throws AeromonException {
        int taskNum = sc.nextInt();
        if (taskNum < 1 || taskNum > tasks.size()) {
            throw new AeromonException("Nani is that task number, sir?");
        } else {
            return taskNum;
        }
    }

    private String getTasksStatus() {
        if (tasks.size() == 0) {
            return "Nicely! No more tasks on the list! Good job! :)";
        } else {
            return String.format("You currently have %d tasks on the list >.< Jiayous", tasks.size());
        }
    }

    private void getFile() {
        try {
            File dataFile = new File("data");
            dataFile.mkdir();
            this.localTasks = new File(dataFile,"localTasks.txt");
            if (localTasks.createNewFile()) {

            } else {
                Scanner fileScanner = new Scanner(localTasks);

                while (fileScanner.hasNextLine()) {
                    String content = fileScanner.nextLine();
                    String[] info = content.split(" / ");
                    switch (info[0]) {
                        case "T": {
                            ToDo task = new ToDo(info[2]);
                            tasks.add(task);
                            break;
                        }
                        case "D": {
                            Deadline task = new Deadline(info[2], info[3]);
                            tasks.add(task);
                            break;
                        }
                        case "E": {
                            Event task = new Event(info[2], info[3]);
                            tasks.add(task);
                            break;
                        }
                    }
                }
                fileScanner.close();
            }
        } catch (IOException e) {
            System.out.println("Ohnoz! Something went terribly wrong!");
            e.printStackTrace();
        }
    }

    private void saveFile() {
        String content = "";
        for (int i = 0; i < tasks.size(); i++) {
            content += tasks.get(i).toOutputFormat() + "\n";
        }

        try {
            FileWriter fileWriter = new FileWriter("./data/localTasks.txt");
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Ohnoz! Something went terribly wrong!");
            e.printStackTrace();
        }
    }
}
