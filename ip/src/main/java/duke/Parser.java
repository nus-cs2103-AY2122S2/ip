package duke;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import duke.Exceptions.*;
import java.util.ArrayList;

// deals with making sense of the user command
public class Parser {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;
    private Scanner sc;

    /**
     * instantiates parser to process user inputs
     * @param Storage storage deals with file writing/reading
     * @param Ui ui which deals with printing messages
     * @parm Scanner sc which scans for user input
     */
    public Parser(Storage storage, Ui ui, TaskList taskList, Scanner sc) {
        this.storage = storage;
        this.ui = ui;
        this.taskList = taskList;
        this.sc = sc;
    }
    /**
     * breaks downs the command given to be processed
     * @param LocalDateTime object
     * @throws InvalidNumberException if input is not a number in the list
     * @throws IOException no file
     * @throws DukeException if command detected but no description appended
     */
    public void parse(String input) throws DukeException, IOException, InvalidNumberException, DeadlineException, EventException, InvalidInstructionException{
        try {
            String[] c = input.split(" ");
            if (c[0].equals("list")) {
                this.ui.printList();
            } else if (c[0].equals("mark")) {
                String number = input.substring(5);
                int index = Integer.parseInt(number);
                if (index < 0 || index > this.taskList.size() - 1) {
                    throw new InvalidNumberException();
                } else {
                    this.ui.markAsDone(index);
                    this.storage.save(); // not done
                }
            }else if(c[0].equals("unmark")){
                String number = input.substring(7);
                int index = Integer.parseInt(number);
                if (index < 0 || index > this.taskList.size() - 1) {
                    throw new InvalidNumberException();
                } else {
                    this.ui.markAsNotDone(index);
                    this.storage.save(); // not done
                }
            } else if (c[0].equals("todo")) {
                if (!(input.substring(4).isBlank())) {
                    Task task = new Todo(input);
                    this.taskList.add(task);
                    this.storage.save();
                    this.ui.printTask(task);
                } else {
                    throw new TodoException();
                }
            } else if (c[0].equals("deadline")) {
                input = input.substring(9);
                if (!(input.isBlank())) {
                    String[] split = input.split("/");
                    String description = split[0].trim();

                    String time = split[1].substring(3) + "/" + split[2] + "/" + split[3];
                    DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                    LocalDateTime time1 = LocalDateTime.parse(time, format1);
                    Task task = new Deadline(description, time1);
                    taskList.add(task);
                    this.storage.save();
                    this.ui.printTask(task);
                } else {
                    throw new DeadlineException();
                }
            } else if (c[0].equals("event")) {
                input = input.substring(6);
                if (!(input.isBlank())) {
                    String[] split = input.split("/");
                    String description = split[0].trim();
                    String time = split[1].substring(3) + "/" + split[2] + "/" + split[3];
                    DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"); // new line
                    LocalDateTime time1 = LocalDateTime.parse(time, format1);  // changes here
                    Task task = new Event(description, time1);
                    this.taskList.add(task);
                    this.storage.save();
                    this.ui.printTask(task);
                } else {
                    throw new EventException();
                }
            } else if (c[0].equals("delete")) {
                String[] s = input.split(" ");
                int index = Integer.parseInt(s[1]);
                if (index < 0 || index > taskList.size()) {
                    throw new InvalidNumberException();
                } else {
                    this.ui.printDeletedTask(index);
                    taskList.deleteTask(index);
                    this.storage.save();
                }
            } else if (c[0].equals("find")) {
                String word = c[1];
                ArrayList<Task> res = new ArrayList<>();
                // get each task from list
                // compare description
                for(int i = 0; i < taskList.size(); i++) {
                    String s = taskList.get(i).getDescription();
                    if (s.contains(word)) {
                        res.add(taskList.get(i));
                    }
                }
                ui.printFind(res);
            }else {
                throw new InvalidInstructionException();
            }
        }catch(DukeException e){
            System.out.println(e.getMessage());
        }
    }
}
