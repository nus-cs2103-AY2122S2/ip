package duke;
import exceptions.DukeDeadlineException;
import exceptions.DukeException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private File file;

    /**
     * Constructor
     * Create a storage file in given filepath
     *
     * @param filepath path to storage file
     * @throws DukeException when does not exist the directory or file
     */
    public Storage(String filepath) throws DukeException {

//        String directory_path = "src/main/data";
//        String directory_path = "./";
//        File directory = new File(directory_path);
//        if (!directory.exists()) {
//            directory.mkdir();
//            throw new DukeException("No such directory but let me make one for you !");
//        }
        File store = new File(filepath);
        try {
            store.createNewFile();
            System.out.println("File is created!");
        } catch (IOException e) {
            throw new DukeException("No such storage file! Or maybe file exists already!");
        }

        this.file = store;
    }

    /**
     * Read throw all Task in Storage.file and return an ArrayList of existing Task
     *
     * @return ArrayList<Task> all Task in Storage.file
     * @throws IOException
     * @throws DukeDeadlineException
     * @throws DukeException
     */
    public ArrayList<Task> getAllTasks() throws IOException, DukeDeadlineException, DukeException {
        ArrayList<Task> all = new ArrayList<Task>();
        Scanner s = new Scanner(file);
        String str;

        while (s.hasNextLine()) {
            str = s.nextLine();
            // str is in format [_type_][_completion_] rest_of_the_task
            // str.split("]", 3) returns [ "[_type_", "[_completion_", " rest_of_the_task" ]

            String[] str_split = str.split("\\]", 3);

            String[] type = str_split[0].split("\\[");
            String[] completion = str_split[1].split("\\[");
            String[] content = str_split[2].split(" ", 2);

            if (type[1].equals("T")) {
                Task todo = new ToDo(content[1]);

                if (completion[1].equals("X")) {
                    todo.markDone();
                }

                all.add(todo);

            } else if (type[1].equals("E")) {
                // format of Event is [E][ ] event_name (at: event_venue)
                // str_split[] = { "[E", "[ ", " event_name (at: event_venue)" }
                // content = { " ", "event_name (at: event_venue)" }
                String separator = " \\(at:";
                String[] get_event_name = content[1].split(separator, 2);
                String event_name = get_event_name[0];
                String event_venue = get_event_name[1].split("\\)")[0];

                Task event = Event.setEvent(event_name + " /at " + event_venue);

                if (completion[1].equals("X")) {
                    event.markDone();
                }

                all.add(event);

            } else if (type[1].equals("D")) {
                // format of Deadline is [D][] task_name (by: deadline_in_yyyy-mm-dd)
                // str_split[] = { "[D", "[ ", " task_name (by: deadline_in_yyyy-mm-dd)" }
                // content = { " ", "task_name (by: deadline_in_yyyy-mm-dd)" }
                String separator = " \\(by:";
                String[] get_task_name = content[1].split(separator, 2);
                String task_name = get_task_name[0];
                String task_by = get_task_name[1].split("\\)")[0];

                // formatting to input
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
                String task_by_date = LocalDate.parse(task_by, formatter).toString();

                Task deadline = Deadline.setDeadline(task_name + " /by " + task_by_date);

                if (completion[1].equals("X")) {
                    deadline.markDone();
                }

                all.add(deadline);

            } else {
                throw new DukeException("Error when reading storage file");
            }

        }
        return all;
    }

    public void writeToFile(TaskList tasklist) throws IOException {
        FileWriter fw = new FileWriter(file);
        ArrayList<Task> all = tasklist.getAllTasks();
        // go through all Task object and write to filepath
        for (int i = 1; i <= all.size(); i++) {
            fw.write(i + ". " + all.get(i - 1));
            fw.write('\n');
        }
        fw.close();
    }
}
