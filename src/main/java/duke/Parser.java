package duke;

import java.time.LocalDate;

public class Parser {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    Parser(Ui ui, TaskList taskList, Storage storage) {
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * if input starts with list, print out all tasks in the list
     * if input starts with mark, mark the task as done
     * if input starts with unmark, unmark the task as not done
     * if input starts with todo, deadline or event, create a corresponding task and add to list
     * if input starts with delete, delete the corresponding task
     * if input starts with check, check all the task on the same date
     * @throws DukeException if wrong input is detected
     */
    String execute(String input) throws DukeException {
        String output;
        try {
            String[] inputArray = input.split(" ", 2);
            String s1 = inputArray[0].toLowerCase();
            if (s1.equals("bye")) {
                output = ui.getLine() + "GoodBye! Thanks for using B.H!" + ui.getLine();
            } else if (s1.equals("list")) {
                output = ui.getLine() + this.taskList.getList() + ui.getLine();
            } else if (s1.equals("mark")) {
                int index = Integer.parseInt(inputArray[1]) - 1;
                if (index < this.taskList.getListSize()) {
                    output = ui.getLine() + "Well done! \n" + this.taskList.mark(index) + ui.getLine();
                } else {
                    output = "Index out of range";
                }
            } else if (s1.equals("unmark")) {
                int index = Integer.parseInt(inputArray[1]) - 1;
                output = "Oh no! \n" + this.taskList.unMark(index);
            } else if (s1.equals("todo")) {
                String task = inputArray[1];
                Task newTask = new Todo(task);
                this.taskList.addToList(newTask);
                output = ui.getLine() + "Task added: " + newTask.toString() + "\n"
                        + "Now you have " + this.taskList.getListSize() + " tasks in the list" + ui.getLine();
            } else if (s1.equals("deadline")) {
                String s = inputArray[1];
                String[] arr = s.split("/by");
                String task = arr[0];
                String time = arr[1];
                Task newTask = new Deadline(task, time);
                this.taskList.addToList(newTask);
                output = ui.getLine() + "Task added:" + newTask.toString() + "\n"
                        + "Now you have " + this.taskList.getListSize() + " tasks in the list" + ui.getLine();
            } else if (s1.equals("event")) {
                String s = inputArray[1];
                String[] arr = s.split("/at");
                String task = arr[0];
                String time = arr[1];
                Task newTask = new Event(task, time);
                this.taskList.addToList(newTask);
                output = ui.getLine() + "Task added:" + newTask.toString() + "\n"
                        + "Now you have " + this.taskList.getListSize() + " tasks in the list" + ui.getLine();
            } else if (s1.equals("delete")) {
                int index = Integer.parseInt(inputArray[1]) - 1;
                output = ui.getLine() + "Okay, I have remove this task:\n"
                        + this.taskList.deleteTask(index) + ui.getLine();
            } else if (s1.equals("check")) {
                LocalDate date = LocalDate.parse(inputArray[1]);
                output = ui.getLine() + this.taskList.checkDate(date) + ui.getLine();
            } else if (s1.equals("find")) {
                String[] arr = input.split(" ", 2);
                String word = arr[1];
                output = ui.getLine() + this.taskList.checkWord(word) + ui.getLine();
            } else {
                output = "Wrong input, please check again";
            }
            this.storage.save(this.taskList.getArrayList());
            return output;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            throw new DukeException("Duke exception!!!");
        }
    }
}
