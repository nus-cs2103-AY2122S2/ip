package duke.storage;

import duke.exception.DukeException;
import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final File file;

    /**
     *
     * @param path
     * @throws IOException
     * @throws DukeException
     *//** Creates a storage file*/
    public Storage(String path) throws IOException, DukeException {
        String[] splits = path.split("/");
        File dir = new File(splits[0]);
        if (!dir.exists()) {
            if (!dir.mkdir()) {
                throw new DukeException("Error in creating ./data folder");
            }
        }

        File savedTasks = new File(path);
        if (!savedTasks.exists()) {
            if (!savedTasks.createNewFile()) {
                throw new DukeException("Error in creating savedTask.txt file");
            }
        }
        this.file = savedTasks;
    }


    /**
     *
     * @return
     * @throws FileNotFoundException
     */
    /** Prints out tasks from the storage file*/

    public ArrayList<Task> readTasks( ) throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();

        Scanner sc = new Scanner(this.file);
        while (sc.hasNext()) {
            String current = sc.nextLine();
            String[] splits = current.split(" ", 3);
            if (splits[0].equals("T")) {
                Task toAdd = new Todo(splits[2], (splits[1].equals("1")));
                tasks.add(toAdd);
            }
            else if (splits[0].equals("E")) {
                Task toAdd = new Event(splits[2], LocalDate.parse(sc.nextLine()), splits[1].equals("1"));
                tasks.add(toAdd);
            }
            else if(splits[0].equals("D")) {
                Task toAdd = new Deadline(splits[2], LocalDate.parse(sc.nextLine()), splits[1].equals("1"));
                tasks.add(toAdd);
            }
            else if (splits[0].equals("A")) {
                Task toAdd = new DoAfterTask(splits[2], LocalDate.parse(sc.nextLine()), splits[1].equals("1"));
                tasks.add(toAdd);
            }
            else {
                System.out.println("Problem encountered when reading file: task unknown");
            }
        }
        return tasks;
    }


    /**
     *
     * @param taskList
     * @throws IOException
     */
    /** Saves tasks to the storage file*/
    public void update(TaskList taskList ) throws IOException {

        StringBuilder sb = new StringBuilder();

        FileWriter fw = new FileWriter(this.file);
        for (Task task : taskList.getTasks()) {
            if (task instanceof Todo) {
                String done = task.isDone() ? "1 " : "0 ";
                String name = task.getName();
                sb.append("T ").append(done).append(name).append(System.lineSeparator());

            } else if (task instanceof Deadline) {
                String done = task.isDone() ? "1 " : "0 ";
                String name = task.getName();
                LocalDate date = ((Deadline) task).getBy();
                sb.append("D ").append(done).append(name).append(System.lineSeparator()).append(date).append(System.lineSeparator());

            } else if (task instanceof Event) {
                String done = task.isDone() ? "1 " : "0 ";
                String name = task.getName();
                LocalDate date = ((Event) task).getAt();
                sb.append("E ").append(done).append(name).append(System.lineSeparator()).append(date).append(System.lineSeparator());

            }
            else {
                String done = task.isDone() ? "1 " : "0 ";
                String name = task.getName();
                LocalDate date = ((DoAfterTask) task).getAfter();
                sb.append("A ").append(done).append(name).append(System.lineSeparator()).append(date).append(System.lineSeparator());

            }
        }
        fw.write(sb.toString());
        fw.close();
    }

 }