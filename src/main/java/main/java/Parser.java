package main.java;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Parser {
    public Storage storage;
    public TaskList taskList;
    public Ui ui;
    public String input;

    public Parser(Storage storage, TaskList taskList, Ui ui, String input) {
        this.storage = storage;
        this.taskList = taskList;
        this.ui = ui;
        this.input = input;
    }

    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        try {
            double d = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public void userCommand() throws IOException, ClassNotFoundException {
        String inputArr[] = input.split(" ", 2);
        if (inputArr[0].equals("bye")) {
            Ui.byeMessage();
            System.exit(0);
        } else if (inputArr[0].equals("list")) {
            Ui.listMessage();
            for (int i = 1; i <= taskList.getTaskList().size(); i++) {
                System.out.println(i + "." + taskList.getTaskList().get(i - 1));
            }
            System.out.println(Ui.line);
        } else if (inputArr[0].equals("mark") && isInteger(inputArr[1])) {
            try {
                int taskNum = Integer.parseInt(inputArr[1]) - 1;
                taskList.getTaskList().get(taskNum).markAsDone();
                Ui.markMessage();
                System.out.println(taskList.getTaskList().get(taskNum) + Ui.line);
                storage.writeToFile(taskList);
            } catch (IndexOutOfBoundsException exp) {
                Ui.markErrorMessage();
            }

        } else if (inputArr[0].equals("unmark") && isInteger(inputArr[1])) {
            try {
                int taskNum = Integer.parseInt(inputArr[1]) - 1;
                taskList.getTaskList().get(taskNum).markAsNotDone();
                Ui.unmarkMessage();
                System.out.println(taskList.getTaskList().get(taskNum) + Ui.line);
                storage.writeToFile(taskList);
            } catch (IndexOutOfBoundsException exp) {
                Ui.markErrorMessage();
            }
        } else if (inputArr[0].equals("todo")) {
            ToDo t = new ToDo(inputArr[1]);
            taskList.addTodoTask(t);
            storage.writeToFile(taskList);

        } else if (inputArr[0].equals("deadline")) {
            try {
                String deadlineArr[] = inputArr[1].split("/by ", 2);
                Deadline d = new Deadline(deadlineArr[0], deadlineArr[1]);
                taskList.addDeadlineTask(d);
                storage.writeToFile(taskList);
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.deadlineErrorMessage();
            } catch (DateTimeParseException e) {
                Ui.localDateErrorMessage();
            }

        } else if (inputArr[0].equals("event")) {
            try {
                String eventArr[] = inputArr[1].split("/at ", 2);
                Event e = new Event(eventArr[0], eventArr[1]);
                taskList.addEventTask(e);
                storage.writeToFile(taskList);
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.eventErrorMessage();
            } catch (DateTimeParseException e) {
                Ui.localDateErrorMessage();
            }

        } else if (inputArr[0].equals("delete") && isInteger(inputArr[1])) {
            try {
                int taskNum = Integer.parseInt(inputArr[1]) - 1;
                taskList.deleteTask(taskNum);
                storage.writeToFile(taskList);
            } catch (IndexOutOfBoundsException exp) {
                Ui.markErrorMessage();
            }

        } else {
            Ui.generalErrorMessage();
        }
    }

}

