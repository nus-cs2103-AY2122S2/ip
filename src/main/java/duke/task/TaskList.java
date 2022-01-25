package duke.task;

import duke.*;
import duke.io.UserInput;
import duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> list = new ArrayList<>(); // arraylist to keep track of task

    public void listTask(UserInput userInput) throws DukeException {
        if (!userInput.getDescription().equals("") || !userInput.getTime().equals("")) {
            // throw wrong command exception
            throw new DukeCommandDoesNotExistException("OOPS!!! This command does not exist.");
        }
        Ui.drawDivider();
        if (list.size() == 0) {
            System.out.println("Nothing on your list!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                System.out.printf("%d.%s%n", i + 1, list.get(i).toString());
            }
        }
        System.out.printf("Now you have %d tasks in the list%n", list.size());
        Ui.drawDivider();
    }

    public void addTask(UserInput userInput) throws DukeException, IOException {
        // add ToDoTask
        if (userInput.getCommand().equals("todo")) {
            if (userInput.getDescription().equals("")) {
                // throw no description exception
                throw new DukeNoDescriptionException("OOPS!!! The description of a todo cannot be empty.");
            }
            Task newTask = new ToDoTask(userInput.getDescription());
            list.add(newTask);
            Ui.drawDivider();
            System.out.println("Got it. I've added this task:");
            System.out.println(newTask);
        }

        // add DeadlineTask
        if (userInput.getCommand().equals("deadline")) {
            if (userInput.getDescription().equals("")) {
                // throw no description exception
                throw new DukeNoDescriptionException("OOPS!!! The description of a deadline cannot be empty.");
            } else if (userInput.getTime().equals("")) {
                // throw no time specified exception
                throw new DukeNoTimeSpecifiedException("OOPS!!! Remember to set a time.");
            }
            Task newTask = new DeadlineTask(userInput.getDescription(), userInput.getTime());
            list.add(newTask);
            Ui.drawDivider();
            System.out.println("Got it. I've added this task:");
            System.out.println(newTask);
        }

        // add EventTask
        if (userInput.getCommand().equals("event")) {
            if (userInput.getDescription().equals("")) {
                // throw no description exception
                throw new DukeNoDescriptionException("OOPS!!! The description of a event cannot be empty.");
            } else if (userInput.getTime().equals("")) {
                // throw no time specified exception
                throw new DukeNoTimeSpecifiedException("OOPS!!! Remember to set a time.");
            }
            Task newTask = new EventTask(userInput.getDescription(), userInput.getTime());
            list.add(newTask);
            Ui.drawDivider();
            System.out.println("Got it. I've added this task:");
            System.out.println(newTask);
        }

        System.out.printf("Now you have %d tasks in the list%n", list.size());
        Ui.drawDivider();
    }

    public void addTaskWithoutMessage(UserInput userInput) throws DukeException {
        // add ToDoTask
        if (userInput.getCommand().equals("todo")) {
            Task newTask = new ToDoTask(userInput.getDescription(), userInput.isDone());
            list.add(newTask);
            System.out.println(newTask);
        }

        // add DeadlineTask
        if (userInput.getCommand().equals("deadline")) {
            Task newTask = new DeadlineTask(userInput.getDescription(), userInput.getTime(), userInput.isDone());
            list.add(newTask);
            System.out.println(newTask);
        }

        // add EventTask
        if (userInput.getCommand().equals("event")) {
            Task newTask = new EventTask(userInput.getDescription(), userInput.getTime(), userInput.isDone());
            list.add(newTask);
            System.out.println(newTask);
        }
    }

    public void markDone(UserInput userInput) throws DukeException {
        if (userInput.getDescription().equals("")) {
            // throw no description exception
            throw new DukeNoDescriptionException("OOPS!!! Please specify a number.");
        }
        int n = Integer.parseInt(userInput.getDescription().replaceAll("[^\\d-]", ""));

        // check if the user's input value is within the range of the list before mark done the task
        checkOutOfBound(n - 1);

        Ui.drawDivider();
        list.get(n - 1).markDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(list.get(n - 1).toString());
        Ui.drawDivider();
    }

    public void markUndone(UserInput userInput) throws DukeException {
        if (userInput.getDescription().equals("")) {
            // throw no description exception
            throw new DukeNoDescriptionException("OOPS!!! Please specify a number.");
        }
        int n = Integer.parseInt(userInput.getDescription().replaceAll("[^\\d-]", ""));

        // check if the user's input value is within the range of the list before mark un done the task
        checkOutOfBound(n - 1);

        Ui.drawDivider();
        list.get(n - 1).markUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(list.get(n - 1).toString());
        Ui.drawDivider();
    }

    public void deleteTask(UserInput userInput) throws DukeException {
        if (userInput.getDescription().equals("")) {
            // throw no description exception
            throw new DukeNoDescriptionException("OOPS!!! Please specify a number.");
        }
        int n = Integer.parseInt(userInput.getDescription().replaceAll("[^\\d-]", ""));

        // check if the user's input value is within the range of the list before deleting the task
        checkOutOfBound(n - 1);

        Ui.drawDivider();
        System.out.println("Noted. I've removed this task:");
        System.out.println(list.get(n - 1).toString());
        list.remove(n - 1);
        System.out.printf("Now you have %d tasks in the list%n", list.size());
        Ui.drawDivider();
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    private void checkOutOfBound(int i) throws DukeOutOfBoundException {
        // check if the user's input value is within the range of the list
        if (i < 0) {
            throw new DukeOutOfBoundException("OOPS!!! Please key in a positive number");
        } else if (i >= list.size()) {
            throw new DukeOutOfBoundException("OOPS!!! Please double check the task number");
        }
    }
}
