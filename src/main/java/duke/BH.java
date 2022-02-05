package duke;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represent a chatbot called BH
 * It is able to read input and proceed with different kind of task and save all the tasks
 */
public class BH {
    private ArrayList<Task> list;
    private Storage storage;
    private Scanner sc = new Scanner(System.in);
    private Ui ui;
    private static final String FILEPATH = "/Users/brandonrhan/Downloads/NUS/CS2103/ip/data/duke.txt";

    /**
     * constructor of BH
     * @throws DukeException if the filepath is invalid
     */
    public BH() throws DukeException {
        this.storage = new Storage(FILEPATH);
        this.list = this.storage.load();
        this.ui = new Ui();
    }

    public String getUiGreet() {
        return this.ui.greet();
    }

    public String getUiLine() {
        return this.ui.getLine();
    }

    /**
     * continue reading input until a bye is detected
     * if input starts with list, print out all tasks in the list
     * if input starts with mark, mark the task as done
     * if input starts with unmark, unmark the task as not done
     * if input starts with todo, deadline or event, create a corresponding task and add to list
     * if input starts with delete, delete the corresponding task
     * if input starts with check, check all the task on the same date
     * @throws DukeException if wrong input is detected
     */
    public String run(String input) throws DukeException {
        String output;
        try {
            String[] inputArray = input.split(" ", 2);
            String s1 = inputArray[0].toLowerCase();
                if (s1.equals("bye")) {
                    output = ui.getLine() + "GoodBye! Thanks for using B.H!" + ui.getLine();
                } else if (s1.equals("list")) {
                    output = ui.getLine() + this.getList() + ui.getLine();
                } else if (s1.equals("mark")) {
                    int index = Integer.parseInt(inputArray[1]) - 1;
                    if (index < this.getListSize()) {
                        output = ui.getLine() + "Well done! \n" + this.mark(index) + ui.getLine();
                    } else {
                        output = "Index out of range";
                    }
                } else if (s1.equals("unmark")) {
                    int index = Integer.parseInt(inputArray[1]) - 1;
                    output = "Oh no! \n" + this.unMark(index);
                } else if (s1.equals("todo")) {
                    String task = inputArray[1];
                    Task newTask = new Todo(task);
                    this.addToList(newTask);
                    output = ui.getLine() + "Task added: " + newTask.toString() + "\n" +
                            "Now you have " + this.getListSize() + " tasks in the list" + ui.getLine();
                } else if (s1.equals("deadline")) {
                    String s = inputArray[1];
                    String[] arr = s.split("/by");
                    String task = arr[0];
                    String time = arr[1];
                    Task newTask = new Deadline(task, time);
                    this.addToList(newTask);
                    output = ui.getLine() + "Task added:" + newTask.toString() + "\n" +
                            "Now you have " + this.getListSize() + " tasks in the list" + ui.getLine();
                } else if (s1.equals("event")) {
                    String s = inputArray[1];
                    String[] arr = s.split("/at");
                    String task = arr[0];
                    String time = arr[1];
                    Task newTask = new Event(task, time);
                    this.addToList(newTask);
                    output = ui.getLine() + "Task added:" + newTask.toString() + "\n" +
                            "Now you have " + this.getListSize() + " tasks in the list" + ui.getLine();
                } else if (s1.equals("delete")) {
                    int index = Integer.parseInt(inputArray[1]) - 1;
                    output = ui.getLine() + "Okay, I have remove this task:\n" +
                            this.deleteTask(index) + ui.getLine();
                } else if (s1.equals("check")) {
                    LocalDate date = LocalDate.parse(inputArray[1]);
                    output  = ui.getLine() + this.checkDate(date) + ui.getLine();
                } else if (s1.equals("find")) {
                    String[] arr = input.split(" ", 2);
                    String word = arr[1];
                    output = ui.getLine() + this.checkWord(word) + ui.getLine();
                } else {
                    output = "Wrong input, please check again";
                }
            this.storage.save(this.list);
            return output;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            throw new DukeException("Duke exception!!!");
        }
    }

    /**
     * check all task in the list and print out all having same date as the input
     *
     * @param date the date to check
     */
    String checkDate(LocalDate date) {
        String validDates = "";
        for (int i = 0; i < this.getListSize(); i++) {
            Task currTask = this.list.get(i);
            if (currTask.getDate().equals(date)) {
                validDates += currTask.toString() + "\n";
            }
        }
        return validDates;
    }

    String checkWord(String word) {
        String validTasks = "";
        for (int i = 0; i < this.getListSize(); i++) {
            if (this.list.get(i).containsWord(word)) {
                validTasks += this.list.get(i).toString() + "\n";
            }
        }
        return validTasks;
    }

    private void addToList(Task task) {
        this.list.add(task);
    }

    private Task deleteTask(int index) {
        Task task =  this.list.get(index);
        this.list.remove(index);
        return task;
    }

    private String getList() {
        String s = "";
        for (int i = 0; i < this.getListSize(); i++) {
            s = s + (i + 1) + ". " + this.list.get(i) + "\n";
        }
        return s;
    }

    /**
     * Returns number of task in the list
     *
     * @return numer of task in the list
     */
    int getListSize() {
        return this.list.size();
    }

    private String mark(int index) {
        this.list.get(index).mark();
        return this.list.get(index).toString();
    }

    private String unMark(int index) {
        this.list.get(index).unMark();
        return this.list.get(index).toString();
    }
}
