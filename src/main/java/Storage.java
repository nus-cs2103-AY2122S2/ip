package duke;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private String filePath;

    /**
     * Initializes the location of the file to retrieve data from.
     * @param filePath is the location of the file, will create a file in this location if it does not already exist.
     */
    Storage(String filePath) {
        this.filePath = filePath;
        try {
            File myObj = new File(this.filePath);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }

    }

    /**
     * Default constructor for Storage object.
     */
    public Storage() {
        try {
            File myObj = new File("data/tasks.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    /**
     * Loads list of tasks saved from previous execution of program.
     *
     * @return a list of Tasks loaded from previous runs
     */
    public duke.TaskList load() {
        duke.TaskList tasks = new duke.TaskList();

        try {
            FileReader fr = new FileReader(this.filePath);
            BufferedReader br = new BufferedReader(fr);
            String currLine;

            while ((currLine = br.readLine()) != null) {
                System.out.println(currLine);


                int taskInt = currLine.indexOf("[") + 1;
                char typeOfTask = currLine.charAt(taskInt);
                int doneInt = taskInt + 3;
                char taskDone = currLine.charAt(doneInt);
                String task = currLine.substring(doneInt + 3);
                if (typeOfTask == 'T') {
                    duke.ToDo toDo = new duke.ToDo(task);
                    if (taskDone == 'X') {
                        toDo.markAsDone();
                    }
                    tasks.add(toDo);
                } else if (typeOfTask == 'D') {
                    duke.Deadline deadline = new duke.Deadline(task);
                    if (taskDone == 'X') {
                        deadline.markAsDone();
                    }
                    tasks.add(deadline);
                } else if (typeOfTask == 'E') {
                    duke.Event event = new duke.Event(task);
                    if (taskDone == 'X') {
                        event.markAsDone();
                    }
                    tasks.add(event);
                }

            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
        return tasks;
    }

    /**
     * Writes the values stored within the tasklist to the file called data/tasks.txt
     * @param list is the list of tasks which are to be saved.
     */
    void save(duke.TaskList list) {
        try {
            FileWriter myWriter = new FileWriter("data/tasks.txt");
            for (int i = 0; i < list.size(); i++) {
                myWriter.write(i + 1 + ". " + list.get(i));
                myWriter.write(String.format("%n"));

            }
            myWriter.close();
            //System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}
